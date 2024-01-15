import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

interface BuyWindowListener {
    void onBuyComplete();
}

public class BuyWindow implements ActionListener {

    private String symbol;
    private Account userAcc;
    double price;

    JPanel cardPanel;

    BuyWindow(String symbol, Account userAcc, double price, JPanel cardPanel) {
        this.symbol = symbol;
        this.userAcc = userAcc;
        this.price = price;
        this.cardPanel = cardPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame buy = new JFrame("Buy " + symbol);
        buy.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buy.setPreferredSize(new Dimension(300, 300));

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JTextField quantityField = new JTextField();
        JButton buyButton = new JButton("Buy");

        panel.add(new JLabel("Enter quantity:"));
        panel.add(quantityField);
        panel.add(buyButton);

        buy.getContentPane().add(panel);

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quantityText = quantityField.getText();
                DBHandler dbHandler = new DBHandler();
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    double quantity = Double.parseDouble(quantityText);
                    if (quantity > 0) {
                        double totalCost = quantity * price;
                        if (totalCost > userAcc.getWallet().getBalance()) {
                            JOptionPane.showMessageDialog(buy, "Invalid quantity. You do not have enough balance.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Path jsonFilePath = Paths.get("C:\\Users\\Rafał\\IdeaProjects\\SilesianPoker\\BiednyBinance\\src\\DB\\DB.json");
                            JsonNode rootNode = objectMapper.readTree(jsonFilePath.toFile());

                            String usernameToFind = userAcc.getUserName();
                            if (rootNode.has("accounts")) {
                                ArrayNode accountsArray = (ArrayNode) rootNode.get("accounts");

                                for (JsonNode accountNode : accountsArray) {
                                    if (accountNode.has("userName") && accountNode.get("userName").asText().equals(usernameToFind)) {
                                        if (!accountNode.has("wallet")) {
                                            ((ObjectNode) accountNode).putObject("wallet");
                                        }

                                        double newBalance = userAcc.getWallet().getBalance() - totalCost;
                                        userAcc.getWallet().setBalance(newBalance);
                                        ((ObjectNode) accountNode.get("wallet")).put("balance", newBalance);

                                        ArrayNode cryptoCurrencies;
                                        if (accountNode.get("wallet").has("cryptoCurrencies") && !accountNode.get("wallet").get("cryptoCurrencies").isNull()) {
                                            cryptoCurrencies = (ArrayNode) accountNode.get("wallet").get("cryptoCurrencies");
                                        } else {
                                            cryptoCurrencies = objectMapper.createArrayNode();
                                            ((ObjectNode) accountNode.get("wallet")).set("cryptoCurrencies", cryptoCurrencies);
                                        }

                                        boolean found = false;
                                        for (JsonNode cryptoCurrencyNode : cryptoCurrencies) {
                                            if (cryptoCurrencyNode.has("symbol") && cryptoCurrencyNode.get("symbol").asText().equals(symbol)) {
                                                double currentQuantity = cryptoCurrencyNode.get("quantity").asDouble();
                                                ((ObjectNode) cryptoCurrencyNode).put("quantity", currentQuantity + quantity);
                                                found = true;
                                                break;
                                            }
                                        }

                                        if (!found) {
                                            ObjectNode newCryptoCurrency = objectMapper.createObjectNode();
                                            newCryptoCurrency.put("symbol", symbol);
                                            newCryptoCurrency.put("purchasePrice", price);
                                            newCryptoCurrency.put("quantity", quantity);

                                            cryptoCurrencies.add(newCryptoCurrency);
                                        }

                                        objectMapper.writeValue(jsonFilePath.toFile(), rootNode);
                                        buy.dispose();
                                        break; // Przerwanie pętli po znalezieniu użytkownika
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(buy, "Invalid quantity. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(buy, "Error processing the request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buy.pack();
        buy.setLocationRelativeTo(null);
        buy.setVisible(true);
    }
}
