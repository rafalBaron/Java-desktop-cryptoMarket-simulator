public class Account {
    private String userName;
    private String password;
    private Wallet wallet;

/*    public Account(String userName, String password, Wallet wallet) {
        this.userName = userName;
        this.password = password;
        this.wallet = wallet;
    }*/

    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
