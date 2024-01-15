import java.util.List;

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
