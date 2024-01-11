import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


class ApiStrike extends SwingWorker<Void, String> {

    private final JPanel panel;

    public ApiStrike(JPanel panel) {
        this.panel = panel;
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
            JLabel label = new JLabel(data);
            panel.removeAll();
            panel.add(label);
            panel.revalidate();
            panel.repaint();
        }
    }

    private String fetchDataFromApi() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com/api/data"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

