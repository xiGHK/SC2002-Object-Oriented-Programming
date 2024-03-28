import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
    



public class CampListReader {



    public static List<Camp> loadCampsFromCSV() {
        List<Camp> camps = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("camplist.csv"))) {
            String line;
            // Read the header line if your CSV file has one
            // line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (true) {
                    String name = data[0].trim();
                    String startDate = data[1].trim();
                    String endDate = data[2].trim();
                    String regCloseDate = data[3].trim();
                    String userGroup = data[4].trim();
                    String location = data[5].trim();
                    int slots = Integer.parseInt(data[6].trim());
                    int campCommSlots = Integer.parseInt(data[7].trim());
                    String description = data[8].trim();
                    String staffUser = data[9].trim();
                    boolean isVisible = Boolean.parseBoolean(data[10].trim());
                    Camp camp = new Camp(name, startDate, endDate, regCloseDate, userGroup, location, slots, campCommSlots, description, staffUser,isVisible);
                    camps.add(camp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return camps;
    }




    public static void saveCampsToCSV(List<Camp> camps) {
        try (FileWriter writer = new FileWriter("camplist.csv")) {
            // If you have a header in your CSV file, write it here
            // writer.write("Name,StartDate,Description,Slots\n");

            for (Camp camp : camps) {
                String line = camp.getCampName() + "," + camp.getStartDate() + "," + camp.getEndDate() + "," + camp.getRegistrationClosingDate() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getCampCommitteeSlots() + "," + camp.getDescription() + "," + camp.getStaffID() + "," + camp.getIsVisible();
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
