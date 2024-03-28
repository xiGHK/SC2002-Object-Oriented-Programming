import java.util.List;
import java.util.ArrayList;

public class StudentViewEnquiries {
    
    Student student;
    List<String> studentEnquiries;

    public StudentViewEnquiries(Student student){
        this.student = student;
        this.studentEnquiries = new ArrayList<>();
    }

    public void ViewEnquiries(){

        
        List<String> allCamps = CampEnquiriesReader.loadCampsFromCSV();
        String reply = null;
        for ( String camps : allCamps){
            String[] line = camps.split(",");

            String campname = line[0];

            for (int i = 2; i<line.length;i++){
                String[] enquiriesnames = line[i].split("/");
                String name = enquiriesnames[0];
                String enquiries = enquiriesnames[1];
                


                if(name.equals(student.getUserID())){
                    if(enquiriesnames.length ==3){
                        reply = enquiriesnames[2];
                       // System.out.println(reply);
                        studentEnquiries.add(campname+","+ enquiries + "," + reply);
                    }
                    else{
                        studentEnquiries.add(campname+","+ enquiries);
                    }
                }
            }
        }
        if(studentEnquiries.size()==0){
            System.out.println("There are no enquiries made by you currently");
            System.out.println("Returning to to Enquiries Page...");
            return;
        }
        int k = 0;
        //System.out.println(studentEnquiries);
        for(String enquiries : studentEnquiries){
            k++;
            String[] data = enquiries.split(",");
            if(data.length == 3){
               // System.out.println("\n" + k +". " + "Enquiry submitted for "+ data[0] + " is: " + data[1] + " --- Processed. Reply received is "+ reply);
                System.out.printf("\n%d. Enquiry submitted for %s is: %-20s -- Processed. Reply received is : %s\n",k,data[0],data[1],data[2]);   
            }
            else{
               // System.out.println("\n" + k +". " + "Enquiry submitted for "+ data[0] + " is: " + data[1]);
                System.out.printf("\n%d. Enquiry submitted for %s is: %-20s\n",k,data[0],data[1]);  
            }

            System.out.println("------------------------------------------------------------------------------");
        }
        
    }

    public List<String> getEnquiries(){
        return new ArrayList<>(studentEnquiries);
    }
}
