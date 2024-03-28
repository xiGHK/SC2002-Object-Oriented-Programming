import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class User {

    private String userID;
    private String password;
    private String faculty;
    Scanner scan = new Scanner(System.in);



    public User(String userID, String password, String faculty){
        this.userID   = userID;
        this.password = password;
        this.faculty  = faculty;
    }

    public User(String userID, String faculty){
        this.userID = userID;
        this.faculty = faculty;
    }
    



    public void passwordChange() {
        
        String filePath = "users_credentials.csv"; 
        List<User> tempUsers = new ArrayList<>();

        // Read the existing data from the CSV and store it in tempUsers.
        tempUsers = readCsv(filePath); // You need to implement this method to read users from the CSV file.

        while (true) {
            System.out.println("\nEnter your old password:");
            System.out.println("To terminate, type in 'terminate'");
            String inputPassword = scan.nextLine();

            if ("terminate".equals(inputPassword)) return;

            if (inputPassword.equals(getPassword())) {
                System.out.println("\nEnter a new password:");
                String newPassword = scan.nextLine();
                this.password = newPassword;
                // Update the password for the current user in the tempUsers list
                for (User tempUser : tempUsers) {
                    if (tempUser.getUserID().equals(getUserID())) {
                        tempUser.setPassword(newPassword);
                        break;
                    }
                }

                // Write the updated list back to the CSV
                writeCredentialsToCsv(filePath, tempUsers);

                System.out.println("Password changed successfully!");
                break;
            } else {
                System.out.println("Wrong password, please try again.\n");
            }
        }

    }



        // Make readCsv a public method that can be accessed from Application
    public static List<User> readCsv(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line if present
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String userID   = values[0];
                String password = values[1];
                String faculty  = values[2];
                String position = values[3];
                if (position.equals("STAFF"))
                    users.add(new Staff(userID,password,faculty));
                else if (position.equals("STUDENT")){
                    
                    users.add(new Student(userID,password,faculty));
                }
                else if (position.equals("COMMITTEE")){
                    users.add(new CampCommitteeMember(userID, password, faculty));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void writeCredentialsToCsv(String filePath, List<User> users) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Write header
            fileWriter.append("UserID,Password,Faculty,Position\n");

            // Write user data
            for (User user : users) {
                if (user instanceof Staff)
                fileWriter.append(user.getUserID()).append(",").append(user.getPassword()).append(",").append(user.getFaculty()).append(",STAFF").append("\n");

                else if (user instanceof Student)
                fileWriter.append(user.getUserID()).append(",").append(user.getPassword()).append(",").append(user.getFaculty()).append(",STUDENT").append("\n");
            }

            System.out.println("User credentials saved to CSV file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }


//-------------SEtters and getters--------------

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getFaculty(){
        return faculty;
    }
    
}
