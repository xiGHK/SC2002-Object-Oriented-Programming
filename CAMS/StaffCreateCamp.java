import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StaffCreateCamp {

    private Staff staffMember;

    public StaffCreateCamp(Staff staff){
        this.staffMember = staff;

    }


    Scanner scan = new Scanner(System.in);

    public Camp createCamp(){
    
    System.out.println("Please input the following information about your camp");
    System.out.println("What will be the name of the camp");
    String campName = scan.nextLine();

    System.out.println("Please input the start date of the camp in YYYY_MM_DD format.");
    String startDate = scan.nextLine();
    while (!DateChecker.isValidDate(startDate)) {
        System.out.println("Date entered in wrong format! Please re-enter start date in YYYY_MM_DD format.");
        startDate = scan.nextLine();
    }

    System.out.println("Please input the end date of the camp in YYYY_MM_DD format.");
    String endDate = scan.nextLine();
    while (!DateChecker.isValidDate(endDate)) {
        System.out.println("Date entered in wrong format! Please re-enter end date in YYYY_MM_DD format.");
        endDate = scan.nextLine();
    }

    while (DateChecker.isFirstDateBeforeSecondDate(endDate, startDate)) {
        System.out.println("End date of camp cannot be before start date! Please re-enter end date in YYYY_MM_DD format.");
        endDate = scan.nextLine();
        while (!DateChecker.isValidDate(endDate)) {
        System.out.println("Date entered in wrong format! Please re-enter end date in YYYY_MM_DD format.");
        endDate = scan.nextLine();
        }
    }

    System.out.println("Please input the closing date for registration in YYYY_MM_DD format.");
    String registrationClosingDate = scan.nextLine();
    while (!DateChecker.isValidDate(registrationClosingDate)) {
        System.out.println("Date entered in wrong format! Please re-enter closing date for registration in YYYY_MM_DD format.");
        registrationClosingDate = scan.nextLine();
    }

    while (DateChecker.isFirstDateBeforeSecondDate(startDate, registrationClosingDate)) {
        System.out.println("Registration closing date of camp cannot be after start date! Please re-enter registration closing date in YYYY_MM_DD format.");
        registrationClosingDate = scan.nextLine();
        while (!DateChecker.isValidDate(registrationClosingDate)) {
        System.out.println("Date entered in wrong format! Please re-enter closing date for registration in YYYY_MM_DD format.");
        registrationClosingDate = scan.nextLine();
        }
    }

    System.out.println("Please input the targeted faculty or whole of NTU");
    String userGroup = scan.nextLine();

    System.out.println("Please input the location for the camp");
    String location = scan.nextLine();

    System.out.println("Please input the total number of slots available for the camp");
    int totalSlots = scan.nextInt(); scan.nextLine();

    System.out.println("Please imput the number of slots available for the camp committee");
    int campCommitteeSlots = scan.nextInt(); scan.nextLine();
    while(campCommitteeSlots > 10){
        System.out.println("Number of camp committee members cannot be more than 10! Please re-enter.");
        campCommitteeSlots = scan.nextInt(); scan.nextLine();
    }

    System.out.println("Please input a description for your camp");
    String description = scan.nextLine();

    String userID = staffMember.getUserID();


    Camp camp = new Camp(campName,startDate,endDate,registrationClosingDate,userGroup,location,totalSlots,
                        campCommitteeSlots,description,userID);
    
    try (FileWriter writer = new FileWriter("camplist.csv",true)) {
        String line = campName + "," + startDate + "," + endDate + "," + registrationClosingDate + "," + userGroup + "," + location + "," + totalSlots + "," + campCommitteeSlots + "," + description + "," + userID + "," + camp.getIsVisible();
        writer.write(line + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }       
    
    
    try (FileWriter writer = new FileWriter("suggestion.csv",true)) {
    	String line = campName;
        writer.write(line + "," + userID + ",");
        for(int i = 0; i<20; i++) {
        	writer.write("-" + ",");
        }
        writer.write("\n");
    } catch (IOException e) {
        e.printStackTrace();
    }    
    

     System.out.println("\nCamp successfully created!");
     
    try (FileWriter writer = new FileWriter("campenquiries.csv",true)) {
    	String line = campName;
        writer.write(line + "," + userID + ",");
        writer.write("\n");
    } catch (IOException e) {
        e.printStackTrace();
    }    
    //createdCamps.add(camp);
    return camp;
    

    }

   
}
