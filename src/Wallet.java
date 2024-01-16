import java.util.List;
import java.util.Objects;

public class Wallet {
    private List<CryptoCurrency> cryptoCurrencies;
    private Double balance;

    public Wallet(List<CryptoCurrency> cryptoCurrencies, Double balance) {
        this.cryptoCurrencies = cryptoCurrencies;
        this.balance = balance;
    }

    public Wallet() {

    }

    public List<CryptoCurrency> getCryptoCurrencies() {
        return cryptoCurrencies;
    }

    public CryptoCurrency getCrypto(String symbol) {

        for (CryptoCurrency c : cryptoCurrencies
             ) {
            if (Objects.equals(c.getSymbol(), symbol)) {
                return c;
            }
        }
        return null;
    }

    public void setCryptoCurrencies(List<CryptoCurrency> cryptoCurrencies) {
        this.cryptoCurrencies = cryptoCurrencies;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
