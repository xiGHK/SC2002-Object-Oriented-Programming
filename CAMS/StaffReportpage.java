import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffReportpage {

    Staff staff;
    Scanner scan = new Scanner(System.in);
    StaffCampViewer campviewer;
    public StaffReportpage(Staff staff){
        this.campviewer = new StaffCampViewer(staff);
        this.staff = staff;
    }


    public void ReportPage(){

        System.out.println("\nWelcome to the Report page!");

        while(true){

            System.out.println("\nStaff Report Page");
            System.out.println("---------------------------------------------------------");
            System.out.println("[1] Generate report for Camp Attendance");
            System.out.println("[2] Generate report for Camp Committee performance");
            System.out.println("[3] Go back to the staff page");
            System.out.println("\nPlease enter a choice");
            System.out.println("---------------------------------------------------------");
            int choice =0;

            try{
                choice = scan.nextInt(); scan.nextLine();
            } catch(InputMismatchException e){
                System.out.println("Invalid input. Only numbers allowed");
                scan.nextLine();
                continue;
            }

            switch(choice){


                case 1:
                campviewer.viewAllCamps();

                System.out.println("To choose the camps to generate report for attendance, please enter 1");
                System.out.println("Else to go back, please enter 2");
                choice = scan.nextInt(); scan.nextLine();
                if(choice == 1){
                    System.out.println("\nPlease choose the camp to generate report");
                    int campchoice = scan.nextInt(); scan.nextLine();
                    campchoice = campchoice -1;
                    StaffGenerateReport generatereport = new StaffGenerateReport((staff));
                    generatereport.GenerateReport(campchoice);

                }
                break;

                case 2:
                    PerformanceReport performanceReport = new PerformanceReport(staff);
                    performanceReport.generatePerformanceReport();
                    break;

                case 3:
                    System.out.println("Returning to staff page...");
                    return;


                default:
                    System.out.println("Invalid input. Your input does not match any of the above options");
                    break;
              
            

            }
        }
    }
    
}
