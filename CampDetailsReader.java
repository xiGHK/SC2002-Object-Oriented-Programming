import java.util.ArrayList;

import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
    





public class CampDetailsReader {

    


    public static List<String> loadCampsFromCSV(){
        List<String> campdetails = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(("campdetails.csv")))){
            String line;
            while((line = br.readLine())!=null)
                campdetails.add(line);
        }catch (IOException e){
            e.printStackTrace();
        }
        return campdetails;
    }

    public static void saveCampsToCSV(List<String> campdetails){
        try(FileWriter writer = new FileWriter("campdetails.csv")){
            for (String line: campdetails){
                writer.write(line+"\n");

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}



