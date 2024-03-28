import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateEnquiryReport {
    
    private Staff staffmember; 
    StaffCampViewer campviewer;
    Scanner scan = new Scanner(System.in);


    public GenerateEnquiryReport(Staff staff){
        staffmember = staff;
        this.campviewer = new StaffCampViewer(staff);

    }

    public void EnquiryReport(int chosen){

        List<Camp> allCamps = CampListReader.loadCampsFromCSV();
        Camp campReport = allCamps.get(chosen);
        System.out.println("\nCamp to generate report is.. : " + campReport.getCampName());

        String staffname = staffmember.getUserID();

        List<String> allcamp = CampEnquiriesReader.loadCampsFromCSV();

        for(String camp : allcamp){
            String[] values = camp.split(",");
            
            
        }

    }




    
}

