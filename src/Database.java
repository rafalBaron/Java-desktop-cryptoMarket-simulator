import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Account> accounts;

    public Database() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getUserByUsernameAndPassword(String username, String password) {
        for (Account account : accounts) {
            if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }
}
