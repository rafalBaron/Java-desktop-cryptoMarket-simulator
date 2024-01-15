import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class CryptoColorDictionary {
    public static Map<String, Color> cryptoColors = new HashMap<>();

    static {
        cryptoColors.put("BTC", new Color(255, 0, 0));  // Red
        cryptoColors.put("ETH", new Color(0, 255, 0));  // Green
        cryptoColors.put("BNB", Color.PINK);  // Pink
        cryptoColors.put("SOL", new Color(255, 255, 0));  // Yellow
        cryptoColors.put("XRP", new Color(255, 165, 0));  // Orange
        cryptoColors.put("LTC", new Color(128, 0, 128));  // Purple
    }

    public static Color getCryptoColor(String symbol) {
        return cryptoColors.getOrDefault(symbol, Color.BLACK);  // Domyślny kolor dla nieznanych symboli
    }
}
