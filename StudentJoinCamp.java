import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentJoinCamp {

    Scanner scan = new Scanner(System.in);

    private Student student; 
    private StudentCampsViewer campviewer;
    public StudentJoinCamp(Student student){
        this.student = student;
        this.campviewer = new StudentCampsViewer(student);
        
    }

    public void joinCamp(){


 


            campviewer.viewAllCamps();   //view all existing camps 
            System.out.println("\n------------------------------------------------");
            System.out.println("Please choose the camp that you want to join");
            System.out.println("To go back to the student page without joining any camps, please enter 0");

            int choice = scan.nextInt(); scan.nextLine();
            if(choice ==0){
                System.out.println("\nReturning to the previous page...");
                return;
            }
            choice = choice -1;
            List<Camp> allCamps = CampListReader.loadCampsFromCSV();
            Camp campToJoin = campviewer.getCreatedCamps().get(choice);
            Camp campToChange = null;

            for(Camp camps : allCamps){
                if (campToJoin.getCampName().equals(camps.getCampName())){
                    campToChange = camps; 
                }
            }
            //get the camp that you want to join from the list of all camps

            System.out.println("\nYou have chosen to join the camp "+campToChange.getCampName());

            //check it is before registration deadline
            if (!DateChecker.beforeRegistrationDeadline(campToChange)){
                System.out.println("You have missed the registration deadline for the camp!");
                return;
            }

            //check if camp is full
            if (campToChange.getTotalSlots() <= 0) {
                System.out.println("The camp is full! Please wait for more slots to open up.");
                return;
            }

            //check if date clashes with students current camps
            if(DateChecker.isDateClashPresent(campToChange, student, campviewer)){
                System.out.println("The camp's dates clashes with one of the camps you have currently signed up for!");
                return;
            }


            try(BufferedReader br = new BufferedReader(new FileReader("campdetails.csv"))){
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    String campname = values[0];
                    if(campToChange.getCampName().equals(campname)){
                        for(String name : values){
                            if( name.equals(student.getUserID()+"/R")){
                                System.out.println("You have joined this camp before and withdrew from it! Can't join again!");
                                return;
                            }
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        //------------------------------------------------------------------------------------------------------------------------------------    

            System.out.println("\nTo join as a camp attendee, please input 1. \nElse to join as a committee member, please input 2.");
            choice = scan.nextInt(); scan.nextLine();

            if(choice == 1){
                System.out.println("\nYou are choosing to join "+campToChange.getCampName()+" as an attendee!");
                System.out.println("Hold on while we process your request...\n");
                List<String> CampDetails = new ArrayList<>();
                boolean Campexists = false;
                boolean Joined = false;
                //get camp details from campdetails.csv. need to update name of student into camp
                try(BufferedReader br = new BufferedReader(new FileReader("campdetails.csv"))){
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");
                        String campname = values[0];
                        if(campToChange.getCampName().equals(campname)){
                            Campexists = true;
                            for(String name : values){
                                if( name.equals(student.getUserID()+"/M")){
                                    Joined = true;
                                    System.out.println("Sorry, but you have already joined this camp");
                                    break;
                                }
                                else if(name.equals(student.getUserID()+"/C")){
                                    Joined = true;
                                    System.out.println("You are already a camp committee member of this camp!");
                                }
                            }
                            if(!Joined){
                                campToChange.decreaseCampSlots();
                                line = line+student.getUserID()+"/M,";
                                Joined = true;
                                System.out.println("You have successfully joined "+campToChange.getCampName());
                            }
                            
                        }               
                        CampDetails.add(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //after updating it, write back to campdetails.
                try(FileWriter writer = new FileWriter("campdetails.csv")){
                    for(String writeline : CampDetails){
                        writer.write(writeline+"\n");
                            
                    }

                        if(!Campexists && !Joined){
                            campToChange.decreaseCampSlots();
                            writer.write(campToChange.getCampName()+","+student.getUserID()+"/M,");
                            System.out.println("You have successfully joined "+campToChange.getCampName());
                        }
                        
                    }catch (IOException e){
                        e.printStackTrace();
                    }                  

                CampListReader.saveCampsToCSV(allCamps);
            }
            //if student wants to join as camp committee member
            else if(choice == 2){  
                boolean committeeStatus = false;
                boolean alreadyMember = false;
            //read from campdetails again to check if its already a member, if its a member, dont let it join as camp committee member. Alot of trouble to swap it.
                try(BufferedReader br = new BufferedReader(new FileReader("campdetails.csv"))){
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");
                        String campname = values[0];
                        if(campToChange.getCampName().equals(campname)){
                            for(String name : values){
                                if( name.equals(student.getUserID()+"/M")){
                                    alreadyMember = true;
                                }
                            }
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
                //check if its already a member
                System.out.println("\nYou are choosing to join "+campToChange.getCampName()+" as a camp committee member!");
                System.out.println("Please hold on while your request is being processed.....");
                //if already a member, skip this wholeeee code. this code initialise the student as a campcommitee member, and update user_credentials
                if(!alreadyMember){
                    List<String> credentials = new ArrayList<>();
                    try(BufferedReader br = new BufferedReader(new FileReader(("users_credentials.csv")))){
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(",");
                            //either staff or student 
                            String name = values [0];
                            if(student.getUserID().equals(name)){
                                if (!values[3].equals("COMMITTEE")){ 
                                    values[3] = "COMMITTEE";
                                    line = values[0] + ","+ values[1] + "," + values[2] + "," + values[3];
                                    System.out.println("Successfully joined as a committee member of "+campToChange.getCampName());
                                    campToChange.decreaseCampCommitteeSlots();
                                }
                                else{
                                    committeeStatus = true;
                                    System.out.println("Request failed. You are already a camp committee member!");
                                }
                            }
                            credentials.add(line);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    try(FileWriter writer = new FileWriter(("users_credentials.csv"))){
                        for( String credential : credentials){
                            writer.write(credential+"\n");
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                //write details back to campdetails again either as attenddeee or commmite

                List<String> CampDetails = new ArrayList<>();
                boolean Campexists = false;
                boolean Joined = false;
                try(BufferedReader br = new BufferedReader(new FileReader("campdetails.csv"))){
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");
                        String campname = values[0];
                        if(campToChange.getCampName().equals(campname)){
                            Campexists = true;
                            for(String name : values){
                                if( name.equals(student.getUserID()+"/M")){
                                    Joined = true;
                                    System.out.println("You are already a attendee in this camp!");
                                    break;
                                }
                            }
                            if(!Joined & !committeeStatus){

                                line = line+student.getUserID()+"/C,";
                                Joined = true;
                            }
                            
                            
                        }               
                    
                        CampDetails.add(line);
                }
                    
                }catch(IOException e){
                    e.printStackTrace();
                }

                try(FileWriter writer = new FileWriter("campdetails.csv")){
                    for(String writeline : CampDetails){
                        writer.write(writeline+"\n");       
                    }
                        if(!Campexists && !Joined){
                            campToChange.decreaseCampSlots();
                            writer.write(campToChange.getCampName()+","+student.getUserID()+"/C,");
                        }
                        
                    }catch (IOException e){
                        e.printStackTrace();
                    }                  

                //save to the camplist, with updated counts of adding students/campcommittee member

                CampListReader.saveCampsToCSV(allCamps);

                
                    try(FileWriter writer = new FileWriter("committeepoints.csv",true)){
                        writer.write(student.getUserID()+ ","+"\n");
                        }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                
            }
        }

        
}
