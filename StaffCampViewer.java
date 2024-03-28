import java.util.ArrayList;
import java.util.List;

public class StaffCampViewer implements CampsViewer{

    private Staff staffMember;

    public StaffCampViewer(Staff staff){
        staffMember = staff;
    }


    public int viewAllCamps(){

        int i = 0;
        List<Camp> createdCamps = getCreatedCamps();
        System.out.println("\nBelow are all the camps created");
        System.out.println("-----------------------------------------------");
        for(Camp camps:createdCamps){
            i++;
            System.out.println(i + ". " + camps.getCampName());
        }

        System.out.println("-----------------------------------------------");

        return i;
    }

    public int viewEveryCamp(){
        List<Camp> camps = CampListReader.loadCampsFromCSV();
        int i = 0;
        System.out.println("\nBelow is every camp");
        System.out.println("-----------------------------------------------");
        for(Camp camp:camps){
            i++;
            System.out.println(i + ". " + camp.getCampName());
        }
        System.out.println("-----------------------------------------------");


        return i;

     
    }


    public List<Camp> getCreatedCamps(){
        List<Camp> camps = CampListReader.loadCampsFromCSV();
        List<Camp> createdCamps = new ArrayList<>();

        for(Camp camp:camps){
            if((camp.getStaffID()).equals(staffMember.getUserID())){
                createdCamps.add(camp);
            }
        }

        return createdCamps;
    }

}
