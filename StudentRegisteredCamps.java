import java.util.ArrayList;
import java.util.List;


public class StudentRegisteredCamps {

    Student student;

    List<String> attendees = new ArrayList<>();
    List<String> campcommittee = new ArrayList<>();

    public StudentRegisteredCamps(Student student){
        this.student = student;
        Initialise();
    }


    public void Initialise(){
        List<String> allcamps = CampDetailsReader.loadCampsFromCSV();
        System.out.println("");


        for(String campdetail : allcamps){
            
            String values[] = campdetail.split(",");

            for(int i =1; i<values.length; i++){
                if( values[i].equals(student.getUserID()+"/M")){
                    attendees.add(values[0]);
                }
                else if (values[i].equals(student.getUserID()+"/C")){
                    campcommittee.add(values[0]);
                }
            }

        }

        
    }   

    public void showAllCampsRegistered(){



        for ( String camps : campcommittee){
            System.out.println("\nCamp Committee of " + camps); 
            System.out.println("----------------------------------");
        }

       

        if(attendees.size() ==0 && campcommittee.size() ==0){
            System.out.println("Student has not joined any camps yet.");
            return;
        }
        
        if(attendees.size()==0){
            System.out.println("Student has not joined any camps as attendee yet");
        }
      
        //System.out.println(attendees);
        int i =0; 
        System.out.println("Attendants of camps:");
        for ( String camps : attendees){
            i++;
            System.out.println(i+ ". " + camps); 
        }
    
                    
    }


    public List<String> getCampAttendees(){
        return attendees;

    }

    public List<String> getCampCommittee(){
        return campcommittee;
    }
}
