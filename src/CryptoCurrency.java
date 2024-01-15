public class CryptoCurrency {
    private String symbol;
    private Float purchasePrice;
    private Float quantity;

    public CryptoCurrency(String symbol, Float purchasePrice, Float quantity) {
        this.symbol = symbol;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
    }

    public CryptoCurrency() {}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

}
