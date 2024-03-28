import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class studentEditSuggestion {
	
	Scanner scan = new Scanner(System.in);
    Suggestion suggestt;
    int c;
    public studentEditSuggestion(Suggestion suggest){
        this.suggestt = suggest;
    }
	
    public void edit(int c) {
    	//System.out.println(c);
    	//suggestt.setSuggestion(c);
    	
    	List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampName().equals(suggestt.getCampName())) {
            	suggestion.setSuggestion(c); 
            	//System.out.println(suggestion.getSuggestion(c));
            }
        }
        
        SuggestionListReader.saveSuggestionsToCSV(suggestions);
    }
    
    public void remove(int c) {
    	//System.out.println(c);
    	//suggestt.setSuggestion(c);
    	
    	List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampName().equals(suggestt.getCampName())) {
            	suggestion.setSuggestionNull(c);
            	suggestion.setStudentID(c, "-");
            	//System.out.println(suggestion.getSuggestion(c));
            }
        }
        
        SuggestionListReader.saveSuggestionsToCSV(suggestions);
    }
}
