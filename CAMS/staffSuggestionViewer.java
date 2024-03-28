import java.util.ArrayList;
import java.util.List;

public class staffSuggestionViewer implements SuggestionsViewer{
	
	private Staff staffMember;
	
	public staffSuggestionViewer(Staff staff) {
		staffMember = staff;
	}
	
	public String [] viewsuggestions(int i) {
		int count = 1;
		int k = 1;
		String[]studentid = new String[10];
		List<Suggestion> createdSuggestions = getCreatedSuggestions();
		System.out.println("\nBelow are all the suggestions you can approve for this camp: ");
		System.out.println("---------------------------------------------------------------");
		for(Suggestion suggestions:createdSuggestions) {
			if(count == i) {
				for(int j=0; j<10; j++) {
	            	if(!suggestions.getStudentID(j).equals("-")) {
	            		System.out.println(k + ". "+suggestions.getStudentID(j)+": "+suggestions.getSuggestion(j));
	            		studentid[k] = suggestions.getStudentID(j);
	            		k++;
	            	}
	            }
			}
			count++;
		}
		return studentid;
	}
	
	public int viewAllSuggestions(){

        int i = 0;
        List<Suggestion> createdSuggestions = getCreatedSuggestions();
        System.out.println("\nBelow are all the suggestions for camps created by you: ");
		System.out.println("----------------------------------------------------------");
		int suggestion = 0;
        for(Suggestion suggestions:createdSuggestions){
            i++;
            System.out.println(i+"."+suggestions.getCampName()+": ");
            for(int j=0; j<10; j++) {
            	if(!suggestions.getStudentID(j).equals("-")) {
            		System.out.println(suggestions.getStudentID(j)+": "+suggestions.getSuggestion(j)+"\n");
					suggestion =1;
            	}
            }
			if(suggestion !=1){
				System.out.println("No suggestions received for this camp yet\n");
			}
        }

        System.out.println("----------------------------------------------\n");

        return i;
    }
	
	
	public List<Suggestion> getCreatedSuggestions(){
        List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
        List<Suggestion> createdSuggestions = new ArrayList<>();

        for(Suggestion suggest:suggestions){
            if((suggest.getStaffUserID()).equals(staffMember.getUserID())){
                createdSuggestions.add(suggest);
            }
        }

        return createdSuggestions;
    }

}
