import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class StaffDeleteCamp {

    private Staff staffmember;
    StaffCampViewer campviewer;
    public StaffDeleteCamp(Staff staff){
        staffmember = staff;
        this.campviewer = new StaffCampViewer(staff);

    }


    public void deleteCamp(int delete){

//------------------For camplist-----------------------------------
        List<Camp> updatedCamps = new ArrayList<>();

        List<Camp> allCamps = CampListReader.loadCampsFromCSV();
        List<Camp> staffCamps = campviewer.getCreatedCamps();
        Camp campToDelete = staffCamps.get(delete);
        System.out.println("Camp to be deleted: "+campToDelete.getCampName());

        for (Camp camp : allCamps){
            if( !(camp.getCampName().equals(campToDelete.getCampName())) ){
                updatedCamps.add(camp);
            }
        }
        CampListReader.saveCampsToCSV(updatedCamps);
//-------------------For camplist----------------------------------------

//---------------------For campdetails------------------------------

        List<String> campdetails = CampDetailsReader.loadCampsFromCSV();
        List<String> newcampdetails = new ArrayList<>();
        for (String line : campdetails){
            String[] campdetail = line.split(",");
            String campname = campdetail[0];
            if( !(campname.equals(campToDelete.getCampName())) ){
                newcampdetails.add(line);
            }


            //---------update usercredentials, change student from committee back to student if it happens to be in acamp thats deleted---------------------      
            else if (campname.equals(campToDelete.getCampName())){
                List<String> namestoremove = new ArrayList<>();
                //need to initialise all students back to student member.
                for(int i = 1; i<campdetail.length;i++){
                    String[] participant = campdetail[i].split("/");
                    String name = participant[0];
                    if( participant[1].equals("C") )
                        namestoremove.add(name);

                  //  for(String nametoremove :namestoremove){
                   //     System.out.println(nametoremove);
                    //}


                              
                    List<String> credentials = new ArrayList<>();
                    for(String nametoremove : namestoremove){
                        try(BufferedReader br = new BufferedReader(new FileReader(("users_credentials.csv")))){
                            String lines;
                            while ((lines = br.readLine()) != null) {
                                String[] values = lines.split(","); 
                                String username = values [0];

                                if(username.equals(nametoremove)){
                                    if (values[3].equals("COMMITTEE")){ 
                                        values[3] = "STUDENT";
                                        lines = values[0] + ","+ values[1] + "," + values[2] + "," + values[3];
                                    }
                                }
                                credentials.add(lines);
                            }
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        try(FileWriter writer = new FileWriter(("users_credentials.csv"))){
                            for( String credential : credentials){
                                writer.write(credential+"\n");
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        credentials = null;
                    }
            //---------update usercredentials, change student from committee back to student if it happens to be in acamp thats deleted--------------------- 
                    
                }
                System.out.println("Camp successfully deleted\n");
            }  

            
        CampDetailsReader.saveCampsToCSV(newcampdetails);
//---------------------For campdetails------------------------------

            
        
        }

        //---------------------for enquiries---------------------------------------------------------
        List<String>campenquiries = CampEnquiriesReader.loadCampsFromCSV();
        List<String> newcampenquiries = new ArrayList<>();
        for (String line : campenquiries){
            String[] campdetail = line.split(",");
            String campname = campdetail[0];
            if( campname.equals(campToDelete.getCampName())){
                    continue;
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
                if( campname.equals(campToDelete.getCampName())){
                    continue;
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


    
    }
}   
    
    
        