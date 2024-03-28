import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StudentEditEnquiries {
    
    Student student;
    List<String> listofEnquiries;
    Scanner scan = new Scanner(System.in);

    public StudentEditEnquiries(Student student, List<String> listofEnquiries){
        this.student = student;
        this.listofEnquiries = listofEnquiries;
    }

    public void EditEnquiries(){
        



        while(true){

            System.out.println("\nPlease choose the enquiry that you want to edit");
            System.out.println("To cancel editing, please enter -1");
            int choice = scan.nextInt();scan.nextLine();
            if(choice == -1)
                return;
            choice = choice -1;

            String enquirytoEdit = listofEnquiries.get(choice);
            String[] data = enquirytoEdit.split(",");
            String campname = data[0];

            if(data.length == 3){
                System.out.println("\nThis Enquiry has already been processed and replied and can't be edited anymore \nPlease choose another enquiry to edit");
                continue;
            }

            String oldenquiry = student.getUserID()+"/"+data[1];
            System.out.println(oldenquiry);


            System.out.println("Please type in the new enquiry for the camp");
            String newEnquiry = scan.nextLine();

            List<String> allcamps = CampEnquiriesReader.loadCampsFromCSV();
            List<String> updatedcamps = new ArrayList<>();

            for (String camp : allcamps){
                String[] values = camp.split(",");
                if(campname.equals(values[0])){  //camp of which to edit the suggestion
                    
                    for(int i = 2;i <values.length;i++){
                        if(values[i].equals(oldenquiry)){
                            values[i] = student.getUserID()+"/" + newEnquiry;
                            camp = "";
                        } 
                    }
                    for (int j = 0; j<values.length;j++){
                    camp = camp+values[j]+",";
                    }
                }
            

                updatedcamps.add(camp);
            }

            CampEnquiriesReader.saveCampsToCSV(updatedcamps);

        }
    }
}
