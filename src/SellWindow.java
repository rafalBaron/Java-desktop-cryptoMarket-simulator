import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

interface SellWindowListener {
    void onBuyComplete();
}

public class SellWindow implements ActionListener {

    private String symbol;
    Account userAcc;
    double price;
    JPanel cardPanel;

    SellWindow(String symbol, Account userAcc, JPanel cardPanel) {
        this.symbol = symbol;
        this.userAcc = userAcc;
        this.cardPanel = cardPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame sell = new JFrame("Sell " + symbol);
        sell.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sell.setPreferredSize(new Dimension(300, 300));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton sellButton = new JButton("Sell");
        sellButton.setForeground(new Color(31, 27, 54));
        sellButton.setBackground(new Color(21, 215, 152));
        sellButton.setFont(new Font("Ubuntu", Font.BOLD, 20));
        sellButton.setFocusPainted(false);
        sellButton.setBorder(null);

        JLabel balance = new JLabel("   Balance   -   " + String.format("%.3f", userAcc.getWallet().getBalance()) + " $");
        balance.setForeground(Color.WHITE);
        balance.setBackground(new Color(31, 27, 54));
        balance.setFont(new Font("Ubuntu", Font.BOLD, 16));
        balance.setBorder(null);

        JLabel worth = new JLabel("   Sell value   -   ");
        worth.setForeground(Color.WHITE);
        worth.setBackground(new Color(31, 27, 54));
        worth.setFont(new Font("Ubuntu", Font.BOLD, 16));
        worth.setBorder(null);

        JTextField quantityField = createPlaceholderTextField("Quantity", worth, this.price);

        panel.add(balance);
        panel.add(quantityField);
        panel.add(worth);
        panel.add(sellButton);

        sell.getContentPane().add(panel);

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quantityText = quantityField.getText();
                DBHandler dbHandler = new DBHandler();
                ObjectMapper objectMapper = new ObjectMapper();
                price = CryptoActualPrices.getCryptoPrice(symbol);

                try {
                    double quantity = Double.parseDouble(quantityText);
                    if (quantity > 0) {
                        double totalCost = quantity * price;
                        if (quantity > userAcc.getWallet().getCrypto(symbol).getQuantity()) {
                            JOptionPane.showMessageDialog(sell, "Too large quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Path jsonFilePath = Paths.get("C:\\Users\\Rafał\\IdeaProjects\\SilesianPoker\\BiednyBinance\\src\\DB\\DB.json");
                            JsonNode rootNode = objectMapper.readTree(jsonFilePath.toFile());

                            String usernameToFind = userAcc.getUserName();
                            if (rootNode.has("accounts")) {
                                ArrayNode accountsArray = (ArrayNode) rootNode.get("accounts");

                                for (JsonNode accountNode : accountsArray) {
                                    if (accountNode.has("userName") && accountNode.get("userName").asText().equals(usernameToFind)) {

                                        double newBalance = userAcc.getWallet().getBalance() + totalCost;
                                        userAcc.getWallet().setBalance(newBalance);
                                        ((ObjectNode) accountNode.get("wallet")).put("balance", newBalance);

                                        ArrayNode cryptoCurrencies = (ArrayNode) accountNode.get("wallet").get("cryptoCurrencies");

                                        boolean found = false;
                                        Iterator<JsonNode> iterator = cryptoCurrencies.iterator();
                                        while (iterator.hasNext()) {
                                            JsonNode cryptoCurrencyNode = iterator.next();
                                            if (cryptoCurrencyNode.has("symbol") && cryptoCurrencyNode.get("symbol").asText().equals(symbol)) {
                                                double currentQuantity = cryptoCurrencyNode.get("quantity").asDouble();
                                                double newQuantity = currentQuantity - quantity;
                                                if (newQuantity > 0) {
                                                    ((ObjectNode) cryptoCurrencyNode).put("quantity", newQuantity);
                                                } else {
                                                    iterator.remove();
                                                }
                                                found = true;
                                                break;
                                            }
                                        }

                                        objectMapper.writeValue(jsonFilePath.toFile(), rootNode);
                                        sell.dispose();
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(sell, "Invalid quantity. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(sell, "Error processing the request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sell.pack();
        sell.setLocationRelativeTo(null);
        sell.setVisible(true);
        sellButton.requestFocusInWindow();
    }
        private JTextField createPlaceholderTextField (String placeholder, JLabel worth, Double price){
            JTextField textField = new JTextField(15);
            textField.setText(placeholder);
            textField.setFont(new Font("Ubuntu", Font.BOLD, 15));
            textField.setForeground(Color.GRAY);
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            textField.setBorder(null);

            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (textField.getText().equals(placeholder)) {
                        textField.setText("");
                        textField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textField.getText().isEmpty()) {
                        textField.setForeground(Color.GRAY);
                        textField.setText(placeholder);
                    }
                }
            });

            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateLabel();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateLabel();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateLabel();
                }

                private void updateLabel() {
                    try {
                        double price = CryptoActualPrices.getCryptoPrice(symbol);
                        Double value = price * Double.parseDouble(textField.getText());

                        worth.setText("   Sell value   -   " + String.format("%.2f", value) + " $");
                    } catch (NumberFormatException ex) {
                        worth.setText("   Sell value   -   ");
                    }
                }
            });

            return textField;
        }
    }
