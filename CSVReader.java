import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    // Make readCsv a public method that can be accessed from Application
    public List<User> readCsv(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line if present
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming the email is the second column and faculty is the third
                String userID = values[1].substring(0, values[1].indexOf('@'));
                String faculty = values[2];
                users.add(new User(userID, faculty));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}