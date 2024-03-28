import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StudentDeleteEnquiries {
    
    Student student;
    List<String> listofEnquiries;
    Scanner scan = new Scanner(System.in);

    public StudentDeleteEnquiries(Student student, List<String> listofEnquiries){
        this.student = student;
        this.listofEnquiries = listofEnquiries;
    }

    public void DeleteEnquiries(){
        

        while(true){
            System.out.println("\nPlease choose the enquiry that you want to delete");
            System.out.println("To cancel this action, please enter -1");

            int choice = scan.nextInt();scan.nextLine();
            if(choice ==-1){
                return;
            }
            choice = choice -1;

            String enquirytoDelete = listofEnquiries.get(choice);
            String[] data = enquirytoDelete.split(",");
            String campname = data[0];

            if(data.length == 3){
                System.out.println("\nThis Enquiry has already been processed and replied and can't be deleted anymore \nPlease choose another enquiry to delete");
                continue;
            }

            String oldenquiry = student.getUserID()+"/"+data[1];
            System.out.println("\nEnquiry to be deleted: "+ data[1]);



            List<String> allcamps = CampEnquiriesReader.loadCampsFromCSV();
            List<String> updatedcamps = new ArrayList<>();

            for (String camp : allcamps){
                String[] values = camp.split(",");
                if(campname.equals(values[0])){  //camp of which to delete the suggestion
                    
                    

                    for(int i = 2;i <values.length;i++){
                        if(values[i].equals(oldenquiry)){
                            values[i] = ""; 
                            camp = "";
                        } 
                    }
                    for (int j = 0; j<values.length;j++){
                        if(!values[j].equals("")){
                            camp = camp+values[j]+",";
                        }
                    }
                }
            

                updatedcamps.add(camp);
            }

            CampEnquiriesReader.saveCampsToCSV(updatedcamps);
            System.out.println("Enquiry succesfully deleted");

        }
    }
}
