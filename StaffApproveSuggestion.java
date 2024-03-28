import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StaffApproveSuggestion {
	
	public void approve(String student) {
		List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        
        for (Suggestion suggestion : suggestions) {
            for(int i=0; i<10;i++) {
            	if (suggestion.getStudentID(i).equals(student)) {
            		suggestion.setSuggestionApprove(i); 
            		//System.out.println(suggestion.getSuggestion(c));
            	}
            }
        }
        System.out.println("Suggestion approved!");
        SuggestionListReader.saveSuggestionsToCSV(suggestions);
    


    List<String> updatedcommitteemember = new ArrayList<>(); 
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
            if(committeevalues[0].equals(student)){ 
                lines = lines +"1,"; 
            } 
            //System.out.println(lines); 
            updatedcommitteemember.add(lines); 
        } 
        //System.out.println(updatedcommitteemember); 
        try(FileWriter writer = new FileWriter("committeepoints.csv")){ 
            for (String line: updatedcommitteemember){ 
                writer.write(line+"\n"); 
                } 
            }catch (IOException e){ 
                e.printStackTrace(); 
            }
	
    }
}
