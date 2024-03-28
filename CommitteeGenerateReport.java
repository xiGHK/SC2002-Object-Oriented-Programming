import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommitteeGenerateReport {
    
    private CampCommitteeMember member; 

    Scanner scan = new Scanner(System.in);


    public CommitteeGenerateReport(CampCommitteeMember member){
        this.member = member;

    }

    public void GenerateReport(){

        Camp campincharge = member.getCampinCharge();

        System.out.println("\nCamp to generate report is.. : " + campincharge.getCampName());
        System.out.println("-----------------------------------------------");

        System.out.println("\n[1] Attendees \n[2] Camp CommitteeMembers");
        System.out.println("\nPlease choose if you want a list of attendees or a list of camp Committee members\n");
        
        int choice = scan.nextInt(); scan.nextLine();


        List<String> campattendees = new ArrayList<>();
        List<String> campcommittee = new ArrayList<>();

        List<String> campdetails = CampDetailsReader.loadCampsFromCSV();
        for(String campdetail : campdetails){
            String values[] = campdetail.split(",");
            if(values[0].equals(campincharge.getCampName())){
                //values 1 to whatever is the attendee/committee name
                for(int i = 1; i<values.length;i++){
                    String[] participant = values[i].split("/");
                    String name = participant[0];
                    if(participant[1].equals("C")){
                        campcommittee.add(name);
                    }
                    else if(participant[1].equals("M")){
                        campattendees.add(name);
                    }
                }
            }

        }
        if(campattendees.size() == 0){
                System.out.println("This Camp does not have attendees yet");
                return;
            }

        if(campcommittee.size() == 0){
                System.out.println("This Camp does not have any camp committee members yet");
                return;
            }



        System.out.println("\n------------------   CAMP "+campincharge.getCampName()+"   ------------------");
        int i = 0;
        if(choice == 2){

            System.out.println("\nCamp Committee Members: ");
            for(String name : campcommittee){
                 i++;
                System.out.println(i+". "+ name);
        }
        }

        if(choice == 1){
          
            System.out.println("\nCamp Attendees");
            for(String name: campattendees){
                i++;
                System.out.println(i+". " + name);
            }
            System.out.println("");
        }
    }



    
}
