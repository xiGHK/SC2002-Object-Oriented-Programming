import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;


public class StudentEnquiriesPage {

    private Student student;
    public StudentEnquiriesPage(Student student){
        this.student = student;
    }


    public void enquiriesPage(){

        Scanner scan = new Scanner(System.in);
        System.out.println("\nWelcome to the Enquiries page!\n");


        

        while(true){
            System.out.println("\nStudent Enquiries Page");
            System.out.println("----------------------------------------");
            System.out.println("[1] View your enquiries");
            System.out.println("[2] Submit your enquiries");
            System.out.println("[3] Edit your enquiries");
            System.out.println("[4] Delete your enquiries");
            System.out.println("[5] To go back to the Student page");
            System.out.println("\nPlease choose an option");
            System.out.println("----------------------------------------");
            int choice =  0;
            
            try{
                choice = scan.nextInt(); scan.nextLine();
            } catch(InputMismatchException e){
                System.out.println("Invalid input! Only numbers are allowed.");
                scan.nextLine();
                continue;
            }

            switch(choice){

                case 1:
                    StudentViewEnquiries viewenquiries = new StudentViewEnquiries(student);
                    viewenquiries.ViewEnquiries();
                    break;

                case 2:
                    StudentSubmitEnquiries enquiries = new StudentSubmitEnquiries(student);
                    enquiries.SubmitEnquiries();
                    break;

                case 3:
                    StudentViewEnquiries viewenquiry = new StudentViewEnquiries(student);
                    viewenquiry.ViewEnquiries();
                    List<String> listofEnquiries = viewenquiry.getEnquiries();
                    StudentEditEnquiries editenquiries = new StudentEditEnquiries(student,listofEnquiries);
                    editenquiries.EditEnquiries();
                    break;
                case 4:
                    StudentViewEnquiries viewenquiry2 = new StudentViewEnquiries(student);
                    viewenquiry2.ViewEnquiries();
                    List<String> listofEnquiries2 = viewenquiry2.getEnquiries();
                    StudentDeleteEnquiries deleteEnquiries = new StudentDeleteEnquiries(student,listofEnquiries2);
                    deleteEnquiries.DeleteEnquiries();
                    break;

                case 5:
                    System.out.println("Going back to the student page...");
                    return;

                default:
                    System.out.println("Invalid input. Your input does not match any of the options");
                    break;

            }
        }
    }
    

}
