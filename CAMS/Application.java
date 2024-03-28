import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main application for a user management system.
 * It handles user login and initialisation of user data from CSV files.
 * The application allows users to login and access specific functionalities based on their role.
 */
public class Application {
    private Scanner scan = new Scanner(System.in);
    private List<Student> students = new ArrayList<>();
    private List<Staff> staffMembers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private CSVReader csvReader = new CSVReader();

    /**
     * The main method to start the application.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Application app = new Application();
        //app.initialiseUsers();
        app.run();
    }

    /**
     * Handles the login process for users.
     * Users are prompted to enter their userID and password.
     * If the credentials are correct, the corresponding User object is returned.
     * 
     * @return The User object if login is successful; null otherwise.
     */
    private User login() {
        String filePath = "users_credentials.csv"; 
        
        for (int i = 0; i < 3; i++) {
            System.out.println("\nPlease input your userID:");
            String inputUserID = scan.nextLine();
            
            System.out.println("Please enter your password:");
            String inputPassword = scan.nextLine();
    
            List<User> allUsers = User.readCsv(filePath);////////////
            
            User user = checkCredentials(inputUserID, inputPassword,allUsers);
            if (user != null) {
                // Correct credentials, return the user object
                System.out.print("\n\nWecome! Login successful!");
                /* 
                if(user instanceof Staff){
                    System.out.println("User is a staff");
                }
                else if (user instanceof CampCommitteeMember){
                    System.out.println("User is a Camp Committee Member");
                }
                else if (user instanceof Student){
                    System.out.println("User is a student");
                } */

                return user;
            } 
            else {
                System.out.printf("Wrong password or Username!, %d more tries!\n", 2 - i);
            }
        }

        System.out.println("failed");
        return null;
        
    
    }

    /**
     * Checks the provided credentials against the list of users.
     * 
     * @param inputUserID The input user ID.
     * @param inputPassword The input password.
     * @param users The list of users to check against.
     * @return The User object if credentials are correct; null otherwise.
     */
    public User checkCredentials(String inputUserID, String inputPassword, List<User> users) {
        // Check against the list of users obtained from the CSV
        for (User user : users) {
            if (user.getUserID().equals(inputUserID) && user.getPassword().equals(inputPassword)) {
                return user; // Correct credentials, return the user object
            }
        }
        // Incorrect credentials
        return null;
    }

    /**
     * Initialises users by loading student and staff data from CSV files.
     * The method reads user data and populates the lists of students and staff members.
     */
    private void initialiseUsers() {
        // Replace these paths with your actual CSV paths
        List<User> studentInfos = csvReader.readCsv("student_list.csv");
        List<User> staffInfos = csvReader.readCsv("staff_list.csv");

        for (User userInfo : studentInfos) {
            students.add(new Student(userInfo.getUserID(),"password", userInfo.getFaculty()));
        }
        for (User userInfo : staffInfos) {
            staffMembers.add(new Staff(userInfo.getUserID(),"password", userInfo.getFaculty()));
        }

        for (Student student : students) {
            users.add(student);
        }
        for (Staff staff : staffMembers) {
            users.add(staff);
        }

        writeCredentialsToCsv("users_credentials.csv",staffMembers,students);


    }


    /**
     * Writes credentials of staff and students to a CSV file.
     * 
     * @param filePath The path of the CSV file.
     * @param staffMembers The list of staff members.
     * @param students The list of students.
     */
    private void writeCredentialsToCsv(String filePath, List<Staff> staffMembers,List<Student> students) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);

            // Writing header
            fileWriter.append("UserID,Password,Faculty,Position\n");

            // Writing user data
            for (User user : staffMembers) {
                fileWriter.append(user.getUserID());
                fileWriter.append(",");
                fileWriter.append(user.getPassword());
                fileWriter.append(",");
                fileWriter.append(user.getFaculty());
                fileWriter.append(",STAFF");
                fileWriter.append("\n");
            }

            for (User user : students) {
                fileWriter.append(user.getUserID());
                fileWriter.append(",");
                fileWriter.append(user.getPassword());
                fileWriter.append(",");
                fileWriter.append(user.getFaculty());
                fileWriter.append(",STUDENT");
                fileWriter.append("\n");
            }

            
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while flushing/closing fileWriter.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs the main loop of the application.
     * This method displays the main menu and handles user input for different functionalities.
     */
private void run() {
        while (true) {
            System.out.println("\n___________________________");
            System.out.println("|      Welcome to CAMs     |");
            System.out.println("----------------------------");
            System.out.println("|       [1] Login          |");
            System.out.println("|       [2] Exit           |");
            System.out.println("|Please choose an option:  |");
            System.out.println("----------------------------");
            int choice = 0;
            try{
                choice = scan.nextInt(); scan.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Only numbers are accepted!");
                scan.nextLine();
                continue;
            }

            if (choice<1 || choice>2 ){
                System.out.println("Invalid Option, please try again");
                continue;
            }
            switch (choice) {

                case 1:
                    User user = login();//login will return either staff or student
                    if (user != null) {
                        if (user instanceof Staff) {
                            ((Staff)user).staffPage();
                        }

                        else if (user instanceof CampCommitteeMember){
                            ((CampCommitteeMember)user).campCommitteePage();
                        }
                        else if (user instanceof Student) {
                            // Handle student login
                            ((Student)user).studentPage();
                        }


                        
                        else
                        System.out.println("user is null");
                        break;

                    }
                    break;
                

                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

}
