import java.util.ArrayList;
import java.util.List;

public class studentSuggestionViewer implements SuggestionsViewer{
	
	private Student student;
	
	public studentSuggestionViewer(Student student) {
		this.student = student;
	}
	
	
	public int viewAllSuggestions(){

        int i = 0;
        List<Suggestion> createdSuggestions = getCreatedSuggestions();
        System.out.println("\nBelow is the suggestion made by you: ");
		System.out.println("---------------------------------------------");

		if(createdSuggestions.size() == 0){
			
			System.out.println("There are no suggestions currently made by you");
			return -1;
		}
        for(Suggestion suggestions:createdSuggestions){
            i++;
            System.out.println("\n" + i+"."+suggestions.getCampName()+"     Suggestion Made: ");
            for(int j=0; j<10; j++) {
            	if(suggestions.getStudentID(j).equals(student.getUserID())) {
            		System.out.println(suggestions.getSuggestion(j));
            	}
            }
        }

        System.out.println("------------------------------\n");

        return i;
    }
	
	public boolean viewSubmittableCamps(String campName) {
		//int i = 0;
		//String [] suggestCampNames = new String[campNames.length];
		List<Suggestion> createdSuggestions = getAllCreatedSuggestions();
        //System.out.println("Below are the camps you can submit your suggestions on: ");
        for(Suggestion suggestions:createdSuggestions){
            for(int j=0; j<10; j++) {
            	int check = 0;
            	if(suggestions.getCampName().equals(campName)) {
            		//System.out.println(suggestions.getCampName());
            		for(int k=0; k<10;k++) {
            			if(suggestions.getStudentID(k).equals(student.getUserID())){
            				return false;
      
            			}
            			else {
            				check++;
            			}
            			
            		}
            		if(check == 10) {
            			return true;
            			//System.out.println(i+". " + suggestions.getCampName());
            			//suggestCampNames[i-1] = suggestions.getCampName();
            		}
            	}
            }
                   
        }
        //System.out.println("Choose the suggestion that you want to edit:");
        //return suggestCampNames;
        return false;
	}
	
	public int viewEdittableCamps(){
        //int i = 0;
        //int k = 0;
        //int [] pos = new int [10];
        List<Suggestion> createdSuggestions = getCreatedSuggestions();
        //System.out.println("Below is the suggestion you can edit: ");
        for(Suggestion suggestions:createdSuggestions){
            //i++;
            //System.out.println(i+". "+suggestions.getCampName()+": ");
            for(int j=0; j<10; j++) {
            	if(suggestions.getStudentID(j).equals(student.getUserID())) {
            		System.out.println("You will be editing suggestion for the following camp: " );
            		System.out.println(suggestions.getCampName()+" : "+ suggestions.getSuggestion(j)+"\n");
            		return j;
            	}
            }
                   
        }
        return -1;
        //System.out.println("Choose the suggestion that you want to edit:");
        //return pos;
        
    }
	public int viewDeletableCamps(){
        
        List<Suggestion> createdSuggestions = getCreatedSuggestions();
        
        for(Suggestion suggestions:createdSuggestions){
            
            for(int j=0; j<10; j++) {
            	if(suggestions.getStudentID(j).equals(student.getUserID())) {
            		System.out.println("You are able to delete the suggestion for the following camp: " );
            		System.out.println(suggestions.getCampName()+" : "+ suggestions.getSuggestion(j)+"\n");
            		return j;
            	}
            }
                   
        }
        return -1;
        //System.out.println("Choose the suggestion that you want to edit:");
        //return pos;
        
    }
	
	 public Suggestion returnSuggestion(){
		 int k = 1;
		 List<Suggestion> createdSuggestions = getCreatedSuggestions();
	        
	        for(Suggestion suggestions:createdSuggestions){
	            for(int j=0; j<10; j++) {
	            	if(suggestions.getStudentID(j).equals(student.getUserID())) {
	            		//if(k == c) {
	            			//Suggestion suggestion = suggestions;
	            			return suggestions;
	            			
	            		//}
	            		//else k++;
	            	}
	            }    
	        }
	        //System.out.println("try again");
	        return null;
	    }
	
	public List<Suggestion> getCreatedSuggestions(){
        List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        List<Suggestion> createdSuggestions = new ArrayList<>();

        for(Suggestion suggest:suggestions){
            for(int i = 0; i<10; i++) {
            	if((suggest.getStudentID(i)).equals(student.getUserID())){
            		createdSuggestions.add(suggest);
            	}
            }
        }

        return createdSuggestions;
    }
	public List<Suggestion> getAllCreatedSuggestions(){
        List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        List<Suggestion> createdSuggestions = new ArrayList<>();

        for(Suggestion suggest:suggestions){
            		createdSuggestions.add(suggest);
            	
            }
        return createdSuggestions;
    }

}