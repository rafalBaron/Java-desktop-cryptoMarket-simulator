import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class ApiStrike extends SwingWorker<Void, String> {

    private final JPanel panel;
    private final JPanel panel1;

    String symbol;

    public ApiStrike(JPanel panel,JPanel panel1,String symbol) {
        this.panel = panel;
        this.panel1 = panel1;
        this.symbol = symbol;
    }

    @Override
    protected Void doInBackground() {
        while (!isCancelled()) {
            try {
                String apiResponse = fetchDataFromApi();
                publish(apiResponse);
                Thread.sleep(10000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void process(java.util.List<String> chunks) {
        for (String data : chunks) {
            JSONObject d = new JSONObject(data);
            String percentStr = d.getString("priceChangePercent");
            double value = Double.parseDouble(percentStr);
            JLabel labelPerc = new JLabel(String.format("%.2f", value) + " %");
            String costStr = d.getString("lastPrice");
            double cost = Double.parseDouble(costStr);
            JLabel labelCost = new JLabel(String.format("%.3f", cost)+" $");
            String wol = d.getString("volume");
            double valWol = Double.parseDouble(costStr);
            JLabel labelWol = new JLabel("Vol:  "+String.format("%.2f", valWol));
            String hPriceStr = d.getString("highPrice");
            double hPrice = Double.parseDouble(hPriceStr);
            JLabel labelHPrice = new JLabel("H:  "+String.format("%.2f", hPrice)+" $");
            String lPriceStr = d.getString("lowPrice");
            double lPrice = Double.parseDouble(lPriceStr);
            JLabel labelLPrice = new JLabel("L:  "+String.format("%.2f", lPrice)+" $");
            if (value < 0) {
                labelPerc.setForeground(Color.RED);
            } else if (value == 0){
                labelPerc.setForeground(Color.GRAY);
            } else {
                labelPerc.setForeground(Color.GREEN);
                String old = labelPerc.getText();
                labelPerc.setText("+"+old);
            }

            labelCost.setForeground(new Color(255, 255, 255));
            labelPerc.setFont(new Font("Ubuntu",Font.BOLD,22));;
            labelCost.setFont(new Font("Ubuntu",Font.BOLD,22));
            labelLPrice.setFont(new Font("Ubuntu",Font.BOLD,13));
            labelLPrice.setForeground(Color.GRAY);
            labelHPrice.setFont(new Font("Ubuntu",Font.BOLD,13));
            labelHPrice.setForeground(Color.GRAY);
            labelWol.setFont(new Font("Ubuntu",Font.BOLD,13));
            labelWol.setForeground(Color.GRAY);


            panel.setBackground(new Color(40, 35, 65));
            panel1.setBackground(new Color(40, 35, 65));
            panel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
            panel.removeAll();
            panel1.removeAll();
            panel.add(labelCost);
            panel.add(labelPerc);
            panel1.add(labelLPrice);
            panel1.add(labelHPrice);
            panel1.add(labelWol);

            panel.revalidate();
            panel.repaint();
            panel1.revalidate();
            panel1.repaint();
        }
    }

    private String fetchDataFromApi() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.binance.com/api/v3/ticker/24hr?symbol="+symbol+"USDT"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

