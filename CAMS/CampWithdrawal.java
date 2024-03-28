import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CampWithdrawal {

    private Student student;
    private StudentRegisteredCamps registeredCamps;
    Scanner scan = new Scanner(System.in);

    public CampWithdrawal(Student student){
        this.student = student;
        registeredCamps = new StudentRegisteredCamps(student);
        
    }

    //increment counts + edit campdetails list
    public void CampWithdraw(){
        

        

        List<String>attendees = registeredCamps.getCampAttendees();



        System.out.println("Attendants of camps:");
        int i = 0;
        for ( String camps : attendees){
            i++;
            System.out.println(i+ ". " + camps); 
        }
        System.out.println("");

        System.out.println("Please choose the camp that you want to withdraw from");
        int withdraw = scan.nextInt() ;scan.nextLine();
        withdraw = withdraw -1;

        String campToWithdraw = attendees.get(withdraw);

        System.out.println("Choosing to withdraw from: "+campToWithdraw);
        System.out.println("Your request is being processed...");

//----------------------------------Update camplist, increment slots------------------------------------------
        List<Camp> allCamps = CampListReader.loadCampsFromCSV();
      //  List<Camp> updatedCamps = new ArrayList<>();
        for(Camp camp: allCamps){
            if ( camp.getCampName().equals(campToWithdraw) ){
                camp.increaseCampSlots();
            }
         //   updatedCamps.add(camp);
        }
        CampListReader.saveCampsToCSV(allCamps);
//------------------------------------------update campdetails, remove name--------------------------------------
//-----------------------update from /M to /R to stand for removed
        List<String> campdetails = CampDetailsReader.loadCampsFromCSV();
        List<String> updatedcampdetails = new ArrayList<>();
        for (String campdetail : campdetails){

            String values[] = campdetail.split(",");

            if (values[0].equals(campToWithdraw)){
                campdetail = "";
                for(i =1; i<values.length; i++){
                    if( values[i].equals(student.getUserID()+"/M")){
                        values[i] = student.getUserID()+"/R";
                    }
                }
                for( i =0; i<values.length;i++){
                    campdetail = campdetail + values[i]+",";
                }
            }
            updatedcampdetails.add(campdetail);
        }

        CampDetailsReader.saveCampsToCSV(updatedcampdetails);
        System.out.println("Successfully withdrew from: " + campToWithdraw);
    }
    
}
