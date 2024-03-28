
import java.util.InputMismatchException;
import java.util.Scanner;



public class Staff extends User{


    Scanner scan = new Scanner(System.in);
    StaffCampViewer campviewer = new StaffCampViewer(this);

    //private List<Camp> createdCamps;

    public Staff(String userID, String password, String faculty){
        super(userID,password,faculty);
        
    }
    
    public void staffPage(){
        System.out.printf("\nHello %s, Welcome to the staff page\n",getUserID());
        System.out.println("Here, you are able perform many functions");
        


        int option =0;

        while(option !=10){
            System.out.println("\nStaff Page");
            System.out.println("--------------------------------");
            System.out.println("[1] Change password\n[2] Create camp \n[3] View all Camps \n[4] View camps created \n[5] Edit camps");
            System.out.println("[6] Delete Camps \n[7] View Suggestions \n[8] Go to Camp enquiries page \n[9] Generate report of camp \n[10] Logout");
                System.out.println("\nPlease choose an option:");
            System.out.println("---------------------------------");
            

            try{
            option = scan.nextInt(); scan.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Only numbers are allowed as input!");
                scan.nextLine();
                continue;
            }
          



            switch(option){

                case 1:
                    this.passwordChange();
                    break;
                
                case 2:
                    StaffCreateCamp createcamppage = new StaffCreateCamp(this);
                    createcamppage.createCamp();
                    System.out.println();
                    break;

                case 3:
                    campviewer.viewEveryCamp();
                    break;
                case 4:
                    campviewer.viewAllCamps();
                    break;

                case 5:
                    campviewer.viewAllCamps();

                    System.out.println("To edit the camps, please enter 1");
                    System.out.println("Else to go back, please enter 2");
                    int edit = scan.nextInt(); scan.nextLine(); 
                    if (edit == 1){
                        StaffEditMenu editpage = new StaffEditMenu(this);
                        editpage.run();
                    }
                    break;
            
                case 6:
                    campviewer.viewAllCamps();

                    System.out.println("To choose the camps to delete, please enter 1");
                    System.out.println("Else to go back, please enter 2");
                    int choice = scan.nextInt(); scan.nextLine();
                    if(choice == 1){
                        System.out.println("Please choose the camp to be deleted");
                        int delete = scan.nextInt(); scan.nextLine();
                        delete = delete-1;
                        StaffDeleteCamp deletecamp = new StaffDeleteCamp(this);
                        deletecamp.deleteCamp(delete);
                        break;

                    }
                    else if (choice ==2){
                        System.out.println("Returning to previous page...");
                        break;
                    }
                    break;
                
                case 7:
                	staffSuggestionViewer suggestionviewer = new staffSuggestionViewer(this);
                	suggestionviewer.viewAllSuggestions();
                	System.out.println("To approve a suggestion, please enter 1");
                    System.out.println("Else to go back, please enter 2");
                    int approve = scan.nextInt(); scan.nextLine();
                    if(approve == 1){
                    	StaffApproveSuggestionMenu approvepage = new StaffApproveSuggestionMenu(this);
                    	approvepage.run();
                    }
                	break;

                case 8:
                    StaffEnquiriesPage enquiriesPage = new StaffEnquiriesPage(this);
                    enquiriesPage.enquiriesPage();
                    break;


                case 9:
                {
                    StaffReportpage reportpage = new StaffReportpage(this);
                    reportpage.ReportPage();
                    break;
                        }

                case 10:
                    System.out.println("Logging out...");
                    return;
                
            }
        }
    }
    
    





}
   

