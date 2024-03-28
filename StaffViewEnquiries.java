
import java.util.List;
import java.util.ArrayList;

public class StaffViewEnquiries {
    
    Staff staff;
    int enquiries= 0;

    public StaffViewEnquiries(Staff staff){
        this.staff = staff;
    }

    public void ViewEnquiries(){

        System.out.println("\nBelow are all the enquiries received by each of the camps created by you!");

        List<String> specificCamps = new ArrayList<>();
        List<String> allCamps = CampEnquiriesReader.loadCampsFromCSV();
        for ( String camps : allCamps){
            String[] line = camps.split(",");

           
            String campcreator = line[1];

            if(campcreator.equals(staff.getUserID())){  //this camp created by the staff.
                specificCamps.add(camps);
            }

        }

        int campno = 0;
       //String reply = null;

        for (String line : specificCamps){
            List<String> studentNames= new ArrayList<>();
            List<String> studentEnquiries = new ArrayList<>();
            List<String> replies = new ArrayList<>();
            campno++;
            String[] values   = line.split(",");
            String campname   = values[0];
            for (int i = 2; i < values.length; i++){                
                String[] data = values[i].split("/");
                String studentName = data[0];
                String enquiry = data[1];
                enquiries = 1;
                
                if (data.length == 3){
                    String reply = data[2];
                    replies.add(reply);
                }
                else{
                    replies.add("");
                }

                studentNames.add(studentName);
                studentEnquiries.add(enquiry);
            }

            System.out.println("\n"+campno + ". " + campname);
            System.out.println("------------------------------------------------------");
            
            if(studentNames.size() == 0){
                System.out.println("No enquiries received for this camp yet!");
            }
            for(int i = 0; i< studentNames.size(); i ++){
                System.out.printf(String.format("UserID: %-10s Enquiry:  %8s",studentNames.get(i), studentEnquiries.get(i)));
                    if(!replies.get(i).equals(""))
                        System.out.println(String.format("       Replied:  %-10s",replies.get(i)));
                    else{
                        System.out.println("");
                    }

                }
            }


        }

        public int getEnquiries(){
            return enquiries;
        }

    }



