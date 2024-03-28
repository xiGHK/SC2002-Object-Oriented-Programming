import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;



public class CampCommitteeMember extends Student {

    Camp campInCharge = null;
    Scanner scan = new Scanner(System.in);

    public CampCommitteeMember(String userID, String password, String faculty){
        super(userID,password,faculty);
    }



    public void campCommitteePage() {

        List<String> campdetails  = CampDetailsReader.loadCampsFromCSV();
        String campName = "";
        for (String camps  : campdetails){
            String[] values = camps.split(",");

            for(int i = 1; i< values.length; i++){
                if(values[i].equals(this.getUserID()+"/C")){
                    campName = values[0];
                }
            }
        }
        

        List<Camp> allCamps = CampListReader.loadCampsFromCSV();
        for (Camp camp :allCamps){
            if(camp.getCampName().equals(campName)){

                campInCharge = camp;
            }
        }

            System.out.printf("\nYou are in charge of %s Camp\n",campInCharge.getCampName());
    	
    	
        while (true) {
            System.out.println("\nCamp Committee Page");
            System.out.println("--------------------------------");
            System.out.println("[1] View Camp Details");
            System.out.println("[2] Submit/edit Suggestion");
            System.out.println("[3] View Suggestion Submitted");
            System.out.println("[4] View Enquiries");
            System.out.println("[5] Reply to Enquiries");
            System.out.println("[6] Generate Report");
            System.out.println("[7] Go to student page");
            System.out.println("[8] Logout");
            System.out.println("\nPlease choose an option: ");
            System.out.println("--------------------------------");
            
            int choice = 0;
            try{
                choice = scan.nextInt(); scan.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Only numbers are allowed");
                scan.nextLine();
                continue;
            }
            

            switch (choice) {
                case 1:
                    viewCampDetails();
                    break;
                case 2:
                	submitSuggestions();
                    break;
                case 3:
                    viewSuggestions();
                    break;
                case 4:
                    CommitteeViewEnquiries viewEnquiries = new CommitteeViewEnquiries(this);
                    viewEnquiries.ViewEnquiries();

                    break;
                case 5:
                    CommitteeReplyEnquiries replyEnquiries = new CommitteeReplyEnquiries(this);
                    replyEnquiries.ReplyEnquiries();
                    break;

                case 6:
                    CommitteeGenerateReport generatereport = new CommitteeGenerateReport(this);
                    generatereport.GenerateReport();
                    break;
                case 7:
                    this.studentPage();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option, your input does not match any of the above options. please try again.");
            }
        }
    }

    private void viewCampDetails() {

        System.out.println("\nDetails of the camp you are in-charge of:\n");
        System.out.print("Camp name:             ");
        System.out.println(campInCharge.getCampName());
        System.out.println("-----------------------------------------------");
        System.out.print("\nCamp start date:            ");
        System.out.println(campInCharge.getStartDate());
        System.out.print("\nCamp end date:              ");
        System.out.println(campInCharge.getEndDate());
        System.out.print("\nRegistration closing date:  ");
        System.out.println(campInCharge.getRegistrationClosingDate());
        System.out.print("\nUser group:                 ");
        System.out.println(campInCharge.getUserGroup());
        System.out.print("\nCamp location:              ");
        System.out.println(campInCharge.getLocation());
        System.out.print("\nTotal slots available:      ");
        System.out.println(campInCharge.getTotalSlots());
        System.out.print("\nCommittee slots available:  ");
        System.out.println(campInCharge.getCampCommitteeSlots());
        System.out.print("\nCamp description:           ");
        System.out.println(campInCharge.getDescription());
        System.out.println("-----------------------------------------------");
        return;
    }

    private void submitSuggestions() {
    	studentEditSuggestionMenu editpage = new studentEditSuggestionMenu(this);
        editpage.run();
    	return;
    }
    
    private void viewSuggestions() {
    	studentSuggestionViewer suggestionviewer = new studentSuggestionViewer(this);
    	suggestionviewer.viewAllSuggestions();
    	return;
    }




    // Override the passwordChange method to restrict camp committee members from changing passwords
    @Override
    public void passwordChange() {
        System.out.println("Camp Committee members are not allowed to change passwords.");
    }

    public Camp getCampinCharge(){
        return campInCharge;
    }
}   