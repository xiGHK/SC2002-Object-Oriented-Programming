import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerformanceReport  {

    Staff staff;
    StaffCampViewer campviewer;
    Scanner scan = new Scanner(System.in);

    public PerformanceReport(Staff staff){
        this.staff = staff;
        this.campviewer = new StaffCampViewer(staff);
    }


    public void generatePerformanceReport(){
        System.out.println("\n[1] Generate the report into a csv file");
        System.out.println(("[2] Generate the report into a txt file"));
        
        System.out.println("\nPlease choose an option");
        System.out.println("---------------------------");
        int choice = scan.nextInt(); scan.nextLine();



        List<Camp> camps = campviewer.getCreatedCamps();
        List<String> campsbyStaff = new ArrayList<>();                   //get a list of campnames created by the staff

        for(Camp camp: camps){
            String campname = camp.getCampName();
            campsbyStaff.add(campname);                           
        }
        //compare that list to campdetails, if same camp name, save
        List<String> allcamps = CampDetailsReader.loadCampsFromCSV();

        List<String> campdetailsbyStaff = new ArrayList<>();

        for(String camp: allcamps){

            String[]values = camp.split(",");

            for(String campname: campsbyStaff){
                if(campname.equals(values[0])){
                    campdetailsbyStaff.add(camp);
                }
                
            }
        }

        
        //now campdetailsbystaff got all lines in campdetails that belong to the staff.

        List <String> campcommittee = new ArrayList<>();

        for(String line : campdetailsbyStaff){
            String[] value = line.split(",");

            for(int i = 1; i<value.length;i++){
                String[] position = value[i].split("/");
                if(position[1].equals("C")){                //camp committee member
                    campcommittee.add(value[0]+"/"+position[0]);
                }
            }
        }

        
        //System.out.println(campcommittee);
        //             campname/committeename



        List<String> committeeList = new ArrayList<>();   //for point system
        try(BufferedReader br = new BufferedReader(new FileReader(("committeepoints.csv")))){
            String line;
            while((line = br.readLine())!=null)
                    committeeList.add(line);
        }catch (IOException e){e.printStackTrace();}
        

        List<String> committeepoints = new ArrayList<>();    //  CAMP/NAME/TOTALPOINTS

        for(String line : campcommittee){
            String[] value = line.split(",");
            for(int i =0 ; i<value.length;i++){

                String[] name = value[i].split("/");

                    for(String lines : committeeList)
                    {
                        String[] data = lines.split(",");
                        String committeename = data[0];
                    
                        if(name[1].equals(committeename)){
                            committeepoints.add(name[0]+"/"+name[1]+"/"+(data.length-1));
                        }
                    }
                    
            }
        }

        String previouscampname = "";
        int j = 0;
        
        if(choice==1){
            System.out.println("\nReport will be saved into a csv file!");
            try (FileWriter writer = new FileWriter("Report.csv")) {
                        // If you have a header in your CSV file, write it here
                        // writer.write("Name,StartDate,Description,Slots\n");

                        for (String info : committeepoints) {
                            writer.write(info + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        }

        if(choice ==2){
            System.out.println("\nReport will be saved into a txt file!");
            try (FileWriter writer = new FileWriter("Report.txt")) {
                        // If you have a header in your CSV file, write it here
                        // writer.write("Name,StartDate,Description,Slots\n");

                        for (String info : committeepoints) {
                            writer.write(info + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        }



        for( String line : committeepoints){
            j++;
            String[] data = line.split(",");
            

            for(int i =0; i <data.length;i++){
                String[] finalvalue = data[i].split("/");
                String camp = finalvalue[0]; String name = finalvalue[1]; String points = finalvalue[2];
                
                if(camp.equals(previouscampname)){
                    System.out.println(String.format("UserID : %-20s Points earned : %s", name, points));
                    continue;
                }

                System.out.println( "\n\n" + (j)+ ". " +"Camp name:    "+camp);
                System.out.println("----------------------------------------------------");
                System.out.println("Camp Commitee Members:");
                System.out.println(String.format("UserID : %-20s Points earned : %s", name, points));

                
                previouscampname = camp;
            }
        }



        //get a list of all the camp committee member belong to the camp created by the staff
        //foor each member of that list, compare against committeepoints 
        //if name matches there, tabulate the total no of 1s, or just line.length to find out how much score they have
        //save that name and that points to two separate array lists
        //then print it out 
                
        /* 
                List<String> allcamps = 
                List<String> committeemember = new ArrayList<>();
                try(BufferedReader br = new BufferedReader(new FileReader(("committeepoints.csv")))){
                    String line;
                    while((line = br.readLine())!=null)
                        committeemember.add(line);
                }catch (IOException e){
                    e.printStackTrace();
                }

                for( String lines : committeemember){
                    String[] committeevalues = lines.split(",");
                    if(committeevalues[0].equals(campCommitteeMember.getUserID())){
                        lines = lines +"1,";

                        */
    }
 
}
