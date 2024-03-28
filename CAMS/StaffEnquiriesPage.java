import java.util.Scanner;

import java.util.InputMismatchException;

public class StaffEnquiriesPage {

    private Staff staff;
    Scanner scan = new Scanner(System.in);
    public StaffEnquiriesPage(Staff staff){
        this.staff = staff;
    }


    public void enquiriesPage(){

        

        
        System.out.println("\nWelcome to the Enquiries page for staff!\n");


        

        while(true){
            System.out.println("\nStaff Enquiry Page");
            System.out.println("------------------------------------------------------");
            System.out.println("[1] View the enquiries received for your camps");
            System.out.println("[2] Reply to enquiries");
            System.out.println("[3] To go back to the Staff page");
            System.out.println("\nPlease choose an option");
            System.out.println("-------------------------------------------------------");
            int choice = 0;

            try{

                choice = scan.nextInt(); scan.nextLine();

            }

            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // clear the buffer to remove the invalid input
                
            }


        

            

            switch(choice){

                case 1:
                    StaffViewEnquiries viewenquiries = new StaffViewEnquiries(staff);
                    viewenquiries.ViewEnquiries();
                    break;

                case 2:
                    StaffReplyEnquiries replyenquiries = new StaffReplyEnquiries(staff);
                    replyenquiries.ReplyEnquiries();
                    break;


                case 3:
                    System.out.println("Going back to the Staff page...");
                    return;

                default:
                    System.out.println("Invalid input. Your input does not match any of the above options");
                    break;

            }
        }
    }
    

}
