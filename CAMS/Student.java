import java.util.InputMismatchException;
import java.util.Scanner;


public class Student extends User{
    Scanner scan = new Scanner(System.in);
    StudentCampsViewer campviewer = new StudentCampsViewer(this);
    

    //private List<Camp> registeredCamps;
    //private Camp committeeCamp;
    //private int committeePoints;

    public Student(String userID, String password, String faculty){
        super(userID,password,faculty);
     //   this.registeredCamps = new ArrayList<>();
      //  this.committeeCamp = null;
       // this.committeePoints = 0;
    }

    public void studentPage(){
      
        System.out.printf("\nHello %s, Welcome to the student page\n",getUserID());
        System.out.println("Here, you can choose to apply for Camps and many other functions.");
        



        while(true){
            System.out.println("\nStudent Page");
            System.out.println("--------------------------------");
            System.out.println("[1] Change Password \n[2] View Camp\n[3] Join Camp \n[4] View Registered Camps");
            System.out.println("[5] Withdraw from camps \n[6] Enter Enquiries page for camps available to you");

            if(this instanceof CampCommitteeMember) System.out.println("[7] Go back to Camp Committee Member page");
            else System.out.println("[7] Logout");
            System.out.println("\nPlease choose an option:");
            System.out.println("--------------------------------");

            
            int option = 0; 
            
            try{
                option = scan.nextInt(); scan.nextLine();
            }catch(InputMismatchException e){
                scan.nextLine();
                System.out.println("\nInvalid input! Only numbers are allowed!");
            }


            switch(option){
                
                case 1:
                    this.passwordChange();
                    break;
                case 2:
                    campviewer.viewAllCamps();
                    break;

                case 3:
                    StudentJoinCamp joincamp = new StudentJoinCamp(this);
                    joincamp.joinCamp();
                    break;

                case 4:
                    StudentRegisteredCamps registeredcamps = new StudentRegisteredCamps(this);
                    registeredcamps.showAllCampsRegistered();
                    break;

                case 5:


                    //attendees =  registeredcamps.viewRegisteredCamps();


                    System.out.println("\nTo confirm withdrawing from camps, please enter 1");
                    System.out.println("Else to go back to the previous page, please enter 2");
                    int choice = scan.nextInt(); scan.nextLine();
                    if(choice ==1){
                        CampWithdrawal campwithdraw = new CampWithdrawal(this);
                        campwithdraw.CampWithdraw();
                    }
                    else if(choice ==2){
                        System.out.println("\nGoing back to previous page...");
                        break;
                    }
                    break;
                
                case 6:
                    StudentEnquiriesPage enquiries = new StudentEnquiriesPage(this);
                    enquiries.enquiriesPage();
                    break;
                case 7:

                    if( this instanceof CampCommitteeMember){
                        return;
                    }
                    else{
                        System.out.println("\nLogging out...");
                        return;
                    }
                default:
                System.out.println("\nInvalid option!");
                    System.out.println("The number you entered does not match any of the choices");
                    break;
                        

            }
        }
    }
    



    


    public void submitenquiries(){

    }



}
