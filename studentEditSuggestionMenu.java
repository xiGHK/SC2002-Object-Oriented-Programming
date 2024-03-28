import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class studentEditSuggestionMenu {
	
	private CampCommitteeMember committeeMember;
	private studentSuggestionViewer suggestionViewer;
	Scanner scan = new Scanner(System.in);
	
	public studentEditSuggestionMenu(CampCommitteeMember committeeMember) {
		this.committeeMember = committeeMember;
		this.suggestionViewer = new studentSuggestionViewer(committeeMember);
	}
	
	public void run() {
		
		while(true) {
			System.out.println("\nWelcome to the suggestion menu!");
			System.out.println("-----------------------------------");
			System.out.println("[1] Submit suggestion");
			System.out.println("[2] Edit suggestion");
			System.out.println("[3] Delete suggestion");
			System.out.println("[4] Return to the Camp Committee page");
			System.out.println("\nPlease choose an option:");
			System.out.println("------------------------------");

			int choice = 0;
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Invalid input! Only numbers are allowed");
				scan.nextLine();
				continue;
			}

			if(choice ==0 || choice>4){
				System.out.println("Invalid input! Your input does not match any of the above choices");
				continue;
			}
            
			if(choice == 4){
                System.out.println("Returning to the previous staff page...");
                return;
            }
            if(choice == 2) {
            	studentSuggestionViewer view = new studentSuggestionViewer(this.committeeMember);
            	int pos = view.viewEdittableCamps();
            	//int c = scan.nextInt();
            	if(pos == -1) {
            		System.out.println("No suggestion made by you, returning...");
            		break;
            	}
            	else {
            		System.out.println("\nPlease choose an option: ");
            		System.out.println("[1] Proceed with the edit ");
            		System.out.println("[2] Return ");
            		int c = scan.nextInt();
            		if(c == 1) {
            			Suggestion suggest = view.returnSuggestion();
            			if(suggest == null) { break;}
                    	else {
                    		studentEditSuggestion edit = new studentEditSuggestion(suggest);
                    		edit.edit(pos);
                    		System.out.println("Suggestion updated!");
                    	}
            		}
            		else if(c == 2) {
            			break;
            		}
            	}
            	
            	
            	
            }
            
            if(choice == 1) {
            	String campName = null;
            	List<String> campdetails  = CampDetailsReader.loadCampsFromCSV();
                
                for (String camps  : campdetails){
                    String[] values = camps.split(",");

                    for(int i = 1; i< values.length; i++){
                        if(values[i].equals(committeeMember.getUserID()+"/C")){
                            campName = values[0];
                            break;
                        }
                    }
                } 
                
                studentSuggestionViewer view = new studentSuggestionViewer(this.committeeMember);
                boolean submittable = view.viewSubmittableCamps(campName);
                if(submittable == true) {
                	System.out.println("\n\nYou will be submitting suggestion for the following camp: " +campName);
                }
                else {
                	System.out.println("You have aready submitted your suggestion, returning...");
                	break;
                }
               
                //for(int i=0; i<10; i++) {
                	//System.out.println(campNames[i]);
                //}
                
                //for(int i = 0; i<campNamesAvail.length; i++) {
                	//System.out.println(campNamesAvail[i]);
                //}
                //int c = scan.nextInt();
                
                List<Suggestion> suggestions = SuggestionListReader.loadsuggestionsFromCSV();
                
                for (Suggestion suggestion : suggestions) {
                    if (suggestion.getCampName().equals(campName)) {
                    	for(int i =0; i<10; i++) {
                    		if(suggestion.getStudentID(i).equals("-")) {
                    			suggestion.setStudentID(i, committeeMember.getUserID());
                    			suggestion.setSuggestion(i);
                    			break;
                    		}
                    	}
                    }
                }
                
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
		            if(committeevalues[0].equals(committeeMember.getUserID())){ 
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
            if(choice == 3) {
            	studentSuggestionViewer view = new studentSuggestionViewer(this.committeeMember);
            	int  pos = view.viewDeletableCamps();
            	Suggestion suggest = view.returnSuggestion();
            	
            	if(pos == -1) {
            		System.out.println("No suggestion made by you, returning...");
            		break;
            	}
            	else {
            		System.out.println("Please choose an option: ");
            		System.out.println("1. Proceed to delete ");
            		System.out.println("2. return ");
            		int c = scan.nextInt();
            		if(c == 1) {
            			
            			if(suggest == null) { break;}
                    	else {
                    		studentEditSuggestion remove = new studentEditSuggestion(suggest);
                    		remove.remove(pos);
                    		System.out.println("Suggestion deleted!");
                    	}
            		}
            		else if(c == 2) {
            			break;
            		}
            	}
            	
            
            }
		}
		
	}
	
}