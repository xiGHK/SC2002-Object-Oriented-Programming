import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SuggestionListReader {
	
	public static List<Suggestion> loadsuggestionsFromCSV() {
        List<Suggestion> suggestions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("suggestion.csv"))) {
            String line;
            // Read the header line if your CSV file has one
            // line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (true) {
                    String campName = data[0].trim();
                    String staffUserID  = data[1].trim();
                    String[] studentUserID = new String[10];
                    String[] suggestion = new String[10];
                    for(int i = 2; i<12; i++) {
                    	//if(data[i].trim() != "-") {
                    		 studentUserID[i-2] = data[i].trim();
                    	//}
                    }
                    for(int i = 12; i<22; i++) {
                    	//if(data[i].trim() != "-") {
                    		 suggestion[i-12] = data[i].trim();
                    	//}
                    }
                    Suggestion suggest = new Suggestion(campName, staffUserID, studentUserID, suggestion);
                    suggestions.add(suggest);
                    
                    	
                    //int regCloseDate = Integer.parseInt(data[2].trim());
                    //String userGroup = data[3].trim();
                    //String location = data[4].trim();
                    //int slots = Integer.parseInt(data[5].trim());
                    //int campCommSlots = Integer.parseInt(data[6].trim());
                    //String description = data[7].trim();
                    //String staffUser = data[8].trim();
                    //boolean isVisible = Boolean.parseBoolean(data[9].trim());
                    //Camp camp = new Camp(name, startDate, regCloseDate, userGroup, location, slots, campCommSlots, description, staffUser,isVisible);
                    //camps.add(camp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suggestions;
    }




    public static void saveSuggestionsToCSV(List<Suggestion> suggestions) {
        try (FileWriter writer = new FileWriter("suggestion.csv")) {
            // If you have a header in your CSV file, write it here
            // writer.write("Name,StartDate,Description,Slots\n");

            for (Suggestion suggest : suggestions) {
            	
            	writer.write(suggest.getCampName() + ",");
                writer.write(suggest.getStaffUserID() + ",");
                for(int i=0; i<10; i++) {
                	if(!suggest.getStudentID(i).equals("-")) {
                		writer.write(suggest.getStudentID(i) + ",");
                	}
                	else writer.write("-" + ",");
                }
                for(int j =0;j<10;j++) {
                	if(!suggest.getSuggestion(j).equals("-")) {
                		writer.write(suggest.getSuggestion(j) + ",");
                	}
                	else writer.write("-" + ",");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
