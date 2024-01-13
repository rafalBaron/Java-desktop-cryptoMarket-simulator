import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class DBHandler {
    private static final String DB_FILE_PATH = "DB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static void addUserToDatabase(User newUser) {
        Database database = readDatabase();
        database.addUser(newUser);
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
