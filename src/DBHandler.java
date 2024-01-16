import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DBHandler {
    public static final URI DB_FILE_PATH1;

    static {
        try {
            DB_FILE_PATH1 = new URI("C:/Users/Rafał/IdeaProjects/SilesianPoker/BiednyBinance/src/DB/DB.json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String DB_FILE_PATH = "src/DB/DB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<Account> getAllUsers() {
        Database database = readDatabase();
        return database.getAccounts();
    }
    public Account getUser(String username, String password) {
        Database database = readDatabase();

        if (database.getAccounts() != null) {
            for (Account account : database.getAccounts()) {
                if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                    return account;
                }
            }
        }

        return null;
    }
    public Account getUser(String username) {
        Database database = readDatabase();

        if (database.getAccounts() != null) {
            for (Account account : database.getAccounts()) {
                if (account.getUserName().equals(username)) {
                    return account;
                }
            }
        }
        return null;
    }

    public void registerUser(String username, String password, double startingBalance) {

        Wallet emptyWallet = new Wallet();
        emptyWallet.setBalance(startingBalance);

        Account newUser = new Account();
        newUser.setUserName(username);
        newUser.setPassword(password);
        newUser.setWallet(emptyWallet);

        addUserToDatabase(newUser);
    }

    private static void addUserToDatabase(Account newAccount) {
        Database database = readDatabase();
        database.addAccount(newAccount);
        saveDatabase(database);
    }

    private static Database readDatabase() {
        try {
            return objectMapper.readValue(new File(DB_FILE_PATH), Database.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Database();
        }
    }

    private static void saveDatabase(Database database) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(DB_FILE_PATH), database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
