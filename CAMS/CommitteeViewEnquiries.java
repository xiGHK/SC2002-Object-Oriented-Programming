
import java.util.List;
import java.util.ArrayList;


public class CommitteeViewEnquiries {

    CampCommitteeMember campCommitteeMember;

    public CommitteeViewEnquiries(CampCommitteeMember campCommitteeMember){
        this.campCommitteeMember = campCommitteeMember;
    }


    public void ViewEnquiries(){

        System.out.println("\nViewing Enquiries made by students for the camp you are in charge of");

        Camp camp = campCommitteeMember.getCampinCharge();
        String campname = camp.getCampName();
        
        List<String> allCamps = CampEnquiriesReader.loadCampsFromCSV();
        String campinCharge = null;
        for(String camps : allCamps){
            String[] campdetail = camps.split(",");

            if(campname.equals(campdetail[0])){
                campinCharge = camps;
            }
        }


        String[] values = campinCharge.split(",");
        
        List<String> names = new ArrayList<>();
        List<String> enquiries = new ArrayList<>();
        List<String> replies = new ArrayList<>();

        for (int i = 2; i<values.length; i++){
            String[] nameEnquiries = values[i].split("/");
            
            if(nameEnquiries.length == 2){   //means no reply yet
                String enquiry = nameEnquiries[1];
                String name = nameEnquiries[0];
                names.add(name);
                enquiries.add(enquiry);
                replies.add("");
            }
            else if(nameEnquiries.length == 3){
                String enquiry = nameEnquiries[1];
                String name = nameEnquiries[0];
                names.add(name);
                enquiries.add(enquiry);
                replies.add(nameEnquiries[2]);
            }
        }


        
        System.out.println("");

        if(names.size() == 0){
            System.out.println("\nThere are no enquiries made by student in this camp currently!");
        }
        for (int i = 1; i<names.size()+1;i++){

            if(replies.get(i-1).equals(""))
                System.out.println(i + ". Student: " + names.get(i-1) + "   Enquiry made: " + enquiries.get(i-1)); 
            else{
                System.out.println(i + ". Student: " + names.get(i-1) + "   Enquiry made: " + enquiries.get(i-1) +"     Replied - " +replies.get(i-1));
            }
        }
        
    }


}

