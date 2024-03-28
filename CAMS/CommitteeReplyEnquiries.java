import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CommitteeReplyEnquiries {
    
    CampCommitteeMember campCommitteeMember;
    Scanner scan = new Scanner(System.in);



    public CommitteeReplyEnquiries(CampCommitteeMember campCommitteeMember){
        this.campCommitteeMember = campCommitteeMember;

    }


    public void ReplyEnquiries(){
        int replied = 0;



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
        List<String> enquiries = new ArrayList<>();
        List<String> names = new ArrayList<>();



        for (int i = 2; i<values.length; i++){
            String[] nameEnquiries = values[i].split("/");
            
            if(nameEnquiries.length == 2){   //means no reply yet
                String enquiry = nameEnquiries[1];
                String name = nameEnquiries[0];
                names.add(name);
                enquiries.add(enquiry);
            }
        }

        if(names.size() == 0){
            System.out.println("\nThere are no enquiries made by student in this camp currently!");
            return;
        }


        System.out.println("\nEnquiries made by students for the camp you are in charge of that have not been answered yet");
        System.out.println("------------------------------------------------------------------------------------------------");
        
        System.out.println("");
        for (int i = 1; i<names.size()+1;i++){

            
            System.out.println(i + ". Student: " + names.get(i-1) + "   Enquiry made: " + enquiries.get(i-1)); 
        }

        

        System.out.println("\nChoose the enquiry that you wish to reply to");
        int choice = scan.nextInt();scan.nextLine();
        choice--;

        String name = names.get(choice);
        String enquiry = enquiries.get(choice);
        System.out.println("\nYou have chosen to reply to the enquiry : " + enquiry + " by " + name); 
        System.out.println("\nPlease type in your reply below");
        String reply = scan.nextLine();


        List<String> updatedcamps = new ArrayList<>();
        List<String> allcamps = CampEnquiriesReader.loadCampsFromCSV();
        for(String line : allcamps){                                   //for that particualr line in campenquiries
            String[] data = line.split(",");                     //split by comma
            if(data[0].equals(campname)){                                 //if first value equal campname desingated by this campcommitteemember
                

                for (int i = 2; i<data.length;i++){                        //then go to that line, and within the length of that line,
                    String[]  nameEnquiries = data[i].split("/");    //split by / again
                    if(name.equals(nameEnquiries[0])){                        //if first value, which is the student name is equal to the student
                        data[i] = nameEnquiries[0] + "/" + nameEnquiries[1] + "/" + reply;
                        line = "";
                        replied = 1;
                    }
                }

                for (int k = 0; k<data.length;k++){
                    line = line+ data[k] + ",";
                }
                
            }
            updatedcamps.add(line);

        }

        CampEnquiriesReader.saveCampsToCSV(updatedcamps);


        if(replied == 1){
            System.out.println("\n------------------------");
            System.out.println("Successfully replied!");
            System.out.println("Returning to the previous page...");

                List<String> updatedcommitteemember = new ArrayList<>();
                List<String> committeemember = new ArrayList<>();
                try(BufferedReader br = new BufferedReader(new FileReader(("committeepoints.csv")))){
                    String line;
                    while((line = br.readLine())!=null)
                        committeemember.add(line);
                }catch (IOException e){
                    e.printStackTrace();
                }

                for( String lines : committeemember){
                    String[] committeevalues = lines.split(",");
                    if(committeevalues[0].equals(campCommitteeMember.getUserID())){
                        lines = lines +"1,";
                    }
                    
                    updatedcommitteemember.add(lines);
                }
                
                try(FileWriter writer = new FileWriter("committeepoints.csv")){
                    for (String line: updatedcommitteemember){
                        writer.write(line+"\n");
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                
        }
        

    }


}
