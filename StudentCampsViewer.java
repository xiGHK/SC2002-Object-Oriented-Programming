import java.util.ArrayList;
import java.util.List;

public class StudentCampsViewer implements CampsViewer{

    private Student student;

    public StudentCampsViewer(Student student){
        this.student = student;
    }


    public int viewAllCamps(){

        int i = 0;
        List<Camp> createdCamps = getCreatedCamps();
        System.out.println("\nBelow are all the camps available to you");
        for(Camp camps:createdCamps){
            i++;
            //System.out.println(i + ". " + camps.getCampName()+ "    Remaining Slots: "+ camps.getTotalSlots() + "       Remaining Camp Committee Slots: "+camps.getCampCommitteeSlots());//show everything else1111

            System.out.printf("\n%d. %-25s User Group: %-13s  Created by Staff: %s\n",i,camps.getCampName(),camps.getUserGroup(),camps.getStaffID());
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("Location: %-17s  Registration closing date: %-30s Description: %s\n",camps.getLocation(),camps.getRegistrationClosingDate(),camps.getDescription());
            System.out.printf("Total Remaining slots: %-4d  Camp committee slots: %-4d Camp Start Date: %-13s Camp End Date: %-13s\n",camps.getTotalSlots(),camps.getCampCommitteeSlots(),camps.getStartDate(),camps.getEndDate());
            
        }

        

        return i;
    }


    public List<Camp> getCreatedCamps(){
        List<Camp> camps = CampListReader.loadCampsFromCSV();
        List<Camp> createdCamps = new ArrayList<>();

        for(Camp camp:camps){
            if( ( (camp.getUserGroup()).equals(student.getFaculty()) || camp.getUserGroup().equals("NTU") ) && camp.getIsVisible() ) {
                createdCamps.add(camp);
            }
        }

        return createdCamps;
    }

}
