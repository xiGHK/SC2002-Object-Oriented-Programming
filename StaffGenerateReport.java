import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffGenerateReport {
    
    private Staff staffmember; 
    StaffCampViewer campviewer;
    Scanner scan = new Scanner(System.in);


    public StaffGenerateReport(Staff staff){
        staffmember = staff;
        this.campviewer = new StaffCampViewer(staff);

    }

    public void GenerateReport(int chosen){

        List<Camp> allCamps = CampListReader.loadCampsFromCSV();
        Camp campReport = allCamps.get(chosen);
        System.out.println("\nCamp to generate report is.. : " + campReport.getCampName());


        System.out.println("[1] Generate report in csv format");
        System.out.println("[2] Generate report in txt format");
        System.out.println("\nPlease choose the format you want your report to be generated in");
        System.out.println("--------------------------------------------------------------------");
        int format = scan.nextInt(); scan.nextLine();




        System.out.println("\nPlease choose if you want a list of attendees or Camp Committee members\n");
        System.out.println("1. Attendees \n2. Camp Committee Members");
        int choice = scan.nextInt(); scan.nextLine();
        if(choice == 1){
            System.out.println("\nShowing Report for attendees..\n");
        }
        if(choice == 2){
            System.out.println("\nShowing Report for Camp Committee Members..\n");
        }


        List<String> campattendees = new ArrayList<>();
        List<String> campcommittee = new ArrayList<>();

        List<String> campdetails = CampDetailsReader.loadCampsFromCSV();
        for(String campdetail : campdetails){
            String values[] = campdetail.split(",");
            if(values[0].equals(campReport.getCampName())){
                //values 1 to whatever is the attendee/committee name
                for(int i = 1; i<values.length;i++){
                    String[] participant = values[i].split("/");
                    String name = participant[0];
                    if(participant[1].equals("C")){
                        campcommittee.add(name);
                    }
                    else if(participant[1].equals("M")){
                        campattendees.add(name);
                    }
                }
            }

        }

        int i = 0;
        if(choice == 2){

            System.out.println("\nCamp Committee Members: ");
            for(String name : campcommittee){
                 i++;
                System.out.println(i+". "+ name);

                if(format ==2){
                        try (FileWriter writer = new FileWriter("Attendance.txt")) {
                            System.out.println("\nAttendance report will be generated in the txt format");
                        // If you have a header in your CSV file, write it here
                        // writer.write("Name,StartDate,Description,Slots\n");
                        writer.write(campReport.getCampName()+ "  Committee members list\n");
                        for (String info : campcommittee) {
                            writer.write(info + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                                if(format ==1){
                        try (FileWriter writer = new FileWriter("Attendance.csv")) {
                                  System.out.println("\nAttendance report will be generated in the csv format");
                        // If you have a header in your CSV file, write it here
                        // writer.write("Name,StartDate,Description,Slots\n");
                        writer.write(campReport.getCampName()+ "  Committee members list\n");
                        for (String info : campcommittee) {
                            writer.write(info + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



        }
        }

        if(choice == 1){
            System.out.println("\nCamp Attendees");
            for(String name: campattendees){
                i++;
                System.out.println(i+". " + name);
            }
            System.out.println("");

                    if(format == 2){

                                        try (FileWriter writer = new FileWriter("Attendance.txt")) {
                                            System.out.println("\nAttendance report will be generated in the txt format");
                            // If you have a header in your CSV file, write it here
                            // writer.write("Name,StartDate,Description,Slots\n");
                                        writer.write(campReport.getCampName()+ "  Attendees list\n");
                            for (String info : campattendees) {
                                writer.write(info + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(format ==1){
                                        try (FileWriter writer = new FileWriter("Attendance.csv")) {
                                            System.out.println("\nAttendance report will be generated in the csv format");
                            // If you have a header in your CSV file, write it here
                            // writer.write("Name,StartDate,Description,Slots\n");
                                        writer.write(campReport.getCampName()+ "  Attendees list\n");
                            for (String info : campattendees) {
                                writer.write(info + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        }
    }




    
}

