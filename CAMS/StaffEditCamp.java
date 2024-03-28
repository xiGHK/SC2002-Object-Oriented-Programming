import java.util.Scanner;

public class StaffEditCamp {

    Scanner scan = new Scanner(System.in);
    Camp camp;
    public StaffEditCamp(Camp camp){
        this.camp = camp;

    }
    public void edit(){
        



        while(true){
            System.out.println("Please choose the option that you want to edit");
            System.out.println("Once done editing, please enter -1");
            int option = scan.nextInt(); scan.nextLine();

            switch(option){

                case 1:
                    System.out.println("Camp name is "+camp.getCampName());
                    camp.setCampName();
                    break;

                case 2:
                    System.out.println("Camp starting date is "+camp.getStartDate());
                    camp.setStartDate();
                    break;

                case 3:
                    System.out.println("Camp ending date is "+camp.getEndDate());
                    camp.setEndDate();
                    break;

                case 4:
                    System.out.println("Camp registration closing date is "+camp.getRegistrationClosingDate());
                    camp.setRegistrationClosingDate();
                    break;

                case 5:
                    System.out.println("Camp is targeted for "+ camp.getUserGroup());
                    camp.setUserGroup();
                    break;

                case 6:
                    System.out.println("Camp is located at "+ camp.getLocation());
                    camp.setLocation();
                    break;

                case 7:
                    System.out.println("Total number of slots available for camp is "+ camp.getTotalSlots());
                    camp.setTotalSlots();
                    break;

                case 8:
                    System.out.println("Total number of committee slots is " + camp.getCampCommitteeSlots());
                    camp.setCampCommitteeSlots();
                    break;

                case 9:
                    System.out.println("Current camp description is "+ camp.getDescription());
                    camp.setDescription();
                    break;

                case 10:
                System.out.println("Camp visibility is set to: "+camp.getIsVisible());
                    camp.toggleVisibility();
                    break;

                case -1:
                    System.out.println("Returning to the menu for editing camps..");
                    return;

                
            }
        }
    }

    
    
    
}
