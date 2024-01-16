import java.util.HashMap;
import java.util.Map;

public class CryptoActualPrices {
    public static Map<String, Float> cryptoPrices = new HashMap<>();

    public static float getCryptoPrice(String symbol) {
        return cryptoPrices.getOrDefault(symbol, 0.f);
    }

    public static void setCryptoPrices(String symbol, double price) {
        cryptoPrices.put(symbol, (float) price);
    }

}
