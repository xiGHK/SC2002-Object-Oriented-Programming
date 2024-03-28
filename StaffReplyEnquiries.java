import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StaffReplyEnquiries {
    
    Staff staff;
    Scanner scan = new Scanner(System.in);
    StaffViewEnquiries viewenquiries;

    public StaffReplyEnquiries(Staff staff){
        this.staff = staff;
        this.viewenquiries = new StaffViewEnquiries(staff);

    }


    public void ReplyEnquiries(){
        
        viewenquiries.ViewEnquiries();

        if(viewenquiries.getEnquiries()==0){
            System.out.println("\nNo enquiries received for any of the camps yet!");
            return;
        }


        System.out.println("\nChoose the camp that you wish to reply enquiries!");
        System.out.println("Else to go back, please input 0.");
        System.out.println("-----------------------------------------------------");
        int choice = scan.nextInt(); scan.nextLine();
        if(choice == 0){
            System.out.println("Returning to the previous page...");
            return;
        }
        choice = choice -1;

        List<String> specificCamps = new ArrayList<>();
        List<String> allCamps = CampEnquiriesReader.loadCampsFromCSV();
        for ( String camps : allCamps){
            String[] line = camps.split(",");
            String campcreator = line[1];
            if(campcreator.equals(staff.getUserID())){  //this camp created by the staff.
                specificCamps.add(camps);
            }
        }

        String campToReply = specificCamps.get(choice);
        //System.out.println(campToReply);

        String[] values = campToReply.split(",");
        String campName = values[0];

        System.out.println("\n------------- Camp " + campName + " -------------");
        int j = 0;
        for( int i = 2; i<values.length;i++){
            j++;
            String[] data = values[i].split("/");
            String student = data[0];
            String enquiry = data[1];
            
            if(data.length == 3){
                String replied = data[2];
                System.out.println(j + ". " + student + " - " + enquiry +  "      Replied: " + replied);
            }
            else{
                System.out.println(j + ". " + student + " - " + enquiry);
            }
            
        }

        System.out.println("\nChoose the enquiry that you wish to reply to");
        choice = scan.nextInt();scan.nextLine();
        choice = choice +1;

        String fullenquiry = values[choice];
        String enquirytoReply = values[choice].split("/")[1];

        if (values[choice].split("/").length == 3){
            System.out.println("You had already replied to this! Please choose another enquiry to reply to!");
            return;
        }

        System.out.println("\nEnquiry made by student is : " + enquirytoReply);
        System.out.println("\nPlease type in your reply to the above enquiry\n");
        String reply = scan.nextLine();

        List<String> updatedcamps = new ArrayList<>();
        List<String> allcamps = CampEnquiriesReader.loadCampsFromCSV();
        for (String camp : allcamps){
            String[] details  = camp.split(","); 
                   // NTU , ARVI , SL22/HELLO , 
            if(details[0].equals(campName)){

                for(int i = 2; i<details.length;i++){
                                   //SL22 / HELLO
                    if(details[i].equals(fullenquiry)){
                        details[i] = details[i] + "/" + reply;
                        camp = "";
                    }
                }
                for (j = 0; j<details.length;j++){
                    camp = camp+details[j]+",";
                }
            }

            updatedcamps.add(camp);

            
        }
        CampEnquiriesReader.saveCampsToCSV(updatedcamps);
        System.out.println("\nSuccessfully replied to the enquiry!");





    }


}
