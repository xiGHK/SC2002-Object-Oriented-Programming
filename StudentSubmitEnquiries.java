import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSubmitEnquiries {

    StudentEnquiriesPage enquiries;
    Student student;
    Scanner scan = new Scanner(System.in);
    StudentCampsViewer campviewer;

    public StudentSubmitEnquiries(Student student){
        this.student = student;
        this.campviewer = new StudentCampsViewer(student);

    }


    public void SubmitEnquiries(){

        campviewer.viewAllCamps();
        List<Camp> allcamps = campviewer.getCreatedCamps();
    
        System.out.println("\n\n-----------------------------------------------------");
        System.out.println("Please choose the camp that you wish to submit an enquiry");
        System.out.println("Else, to cancel your action, please enter 0");
        int choice = scan.nextInt(); scan.nextLine();
        if(choice ==0){
            System.out.println("Returning to previous page...");
            return;
        }
        choice = choice -1;

        List<String> updatedCamps = new ArrayList<>();
        List<String> allCamps = CampEnquiriesReader.loadCampsFromCSV();
        for(String line : allCamps){
            String values[] = line.split(",");
            if( allcamps.get(choice).getCampName().equals(values[0]) ){
                boolean submitted = false;
                for(int j = 2; j<values.length; j++){
                    String[] names = values[j].split("/");
                    if(names[0].equals(student.getUserID())){
                        System.out.println("You had already submitted an enquiry for this camp! However, you can still edit or delete your enquiry\n");
                        submitted = true;
                        break;
                    }
                }
                if(!submitted){
                    System.out.println("Please type in your enquiry");
                    String enquiry = scan.nextLine();
                    line = line +student.getUserID()+"/"+enquiry+","; 
                    System.out.println("\nEnquiry successfully submitted!");
                }
            }
            updatedCamps.add(line);
        }


        CampEnquiriesReader.saveCampsToCSV(updatedCamps);
        //just to check that the camp exists.



    }
    
}
