import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class StaffEditMenu{

    
    private Staff staffMember;
    private StaffCampViewer campviewer;
    Scanner scan = new Scanner(System.in);

    public StaffEditMenu(Staff staff){
        this.staffMember = staff;
        this.campviewer = new StaffCampViewer(staffMember);

    }





    public void run(){
        


        while(true){

            System.out.println("\nWelcome to the menu for editing your camps!");
            int totalcamps = campviewer.viewAllCamps();
            System.out.println("Please choose the camp that you want to edit");
            System.out.println("To go back to the previous Staff page, please enter -1");

            int choice = scan.nextInt(); scan.nextLine();
            if(choice == -1){
                System.out.println("Returning to the previous staff page...");
                return;
            }

            choice = choice -1;

            Camp campChosen  = campviewer.getCreatedCamps().get(choice);
            campChosen.showCampInfo();
            List<Camp> camps = CampListReader.loadCampsFromCSV();
            Camp campToEditInList = null;
            for (Camp camp : camps) {
                if (camp.getCampName().equals(campChosen.getCampName())) {
                    campToEditInList = camp;
                    break;
                }
            }

            if (campToEditInList != null) {
                // Make the necessary changes
                String previousCampName = campToEditInList.getCampName();

                StaffEditCamp editcamp = new StaffEditCamp(campToEditInList);
                editcamp.edit();
                Camp newCampinfo = campToEditInList;
                String newCampName = newCampinfo.getCampName();
                campToEditInList.showCampInfo();
                
                // Save the updated camps back to the camp list CSV file
                CampListReader.saveCampsToCSV(camps);
                
//------------------------need to edit the list for campdetails as well-----------------

                List<String> campdetails = CampDetailsReader.loadCampsFromCSV();
                List<String> newcampdetails = new ArrayList<>();
                for (String line : campdetails){
                    String[] campdetail = line.split(",");
                    String campname = campdetail[0];
                    if( campname.equals(previousCampName)){
                        for(int i = 1; i<campdetail.length;i++){
                            newCampName+= "," + campdetail[i];  //campname + rest of particpants
                        }
                        newCampName = newCampName+",";
                        line = newCampName;
                    }
                    newcampdetails.add(line);
                }

                CampDetailsReader.saveCampsToCSV(newcampdetails);

                //---------------------for enquiries---------------------------------------------------------
                List<String>campenquiries = CampEnquiriesReader.loadCampsFromCSV();
                List<String> newcampenquiries = new ArrayList<>();
                for (String line : campenquiries){
                    String[] campdetail = line.split(",");
                    String campname = campdetail[0];
                    if( campname.equals(previousCampName)){
                        for(int i = 1; i<campdetail.length;i++){
                            newCampName+= "," + campdetail[i];  //campname + rest of particpants
                        }
                        newCampName = newCampName+",";
                        line = newCampName;
                    }

                    newcampenquiries.add(line);
                }

                CampEnquiriesReader.saveCampsToCSV(newcampenquiries);


                //-------------------for suggestions-------------------------------------------------
                    
                    List<String> campsuggestions = new ArrayList<>();
                    try(BufferedReader br = new BufferedReader(new FileReader(("suggestion.csv")))){
                        String line;
                        while((line = br.readLine())!=null)
                            campsuggestions.add(line);
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    List<String>newcampsuggestions = new ArrayList<>();

                    for (String line : campsuggestions){
                        String[] campdetail = line.split(",");
                        String campname = campdetail[0];
                        if( campname.equals(previousCampName)){
                            for(int i = 1; i<campdetail.length;i++){
                                newCampName+= "," + campdetail[i];  //campname + rest of particpants
                            }
                            newCampName =newCampName+",";
                            line = newCampName;
                        }
                        newcampsuggestions.add(line);
                }

                    try(FileWriter writer = new FileWriter("suggestion.csv")){
                        for (String line: newcampsuggestions){
                            writer.write(line+"\n");

                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
    


                 
            } else {
                System.out.println("Camp not found with the specified ID.");
            }

        }

    }
}


    

