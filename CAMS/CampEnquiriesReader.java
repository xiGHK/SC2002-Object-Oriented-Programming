import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
    





public class CampEnquiriesReader {

    


    public static List<String> loadCampsFromCSV(){
        List<String> campenquiries = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(("campenquiries.csv")))){
            String line;
            while((line = br.readLine())!=null)
                campenquiries.add(line);
        }catch (IOException e){
            e.printStackTrace();
        }
        return campenquiries;
    }

    public static void saveCampsToCSV(List<String> campdetails){
        try(FileWriter writer = new FileWriter("campenquiries.csv")){
            for (String line: campdetails){
                writer.write(line+"\n");

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    
}
