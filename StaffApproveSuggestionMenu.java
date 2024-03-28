
import java.util.Scanner;


public class StaffApproveSuggestionMenu {
	private Staff staffMember;
    private staffSuggestionViewer suggestionviewer;
    Scanner scan = new Scanner(System.in);

    public StaffApproveSuggestionMenu(Staff staff){
        this.staffMember = staff;
        this.suggestionviewer = new staffSuggestionViewer(staffMember);

    }
    
    public void run(){
    	while(true) {
    		System.out.println("\nWelcome to the suggestion approval menu! Enter -1 to exit.");
    		staffSuggestionViewer suggestionviewer = new staffSuggestionViewer(this.staffMember);
        	suggestionviewer.viewAllSuggestions();
        	System.out.println("Choose the camp: ");
        	int campNo = scan.nextInt();
        	if(campNo == -1){
                System.out.println("Returning to the previous staff page...");
                return;
            }
        	String[]studentid = suggestionviewer.viewsuggestions(campNo);
        	System.out.println("Choose the suggestion to approve: ");
        	int suggestNo = scan.nextInt();
        	if(suggestNo == -1){
                System.out.println("Returning to the previous staff page...");
                return;
            }
        	String student = studentid[suggestNo];
        	StaffApproveSuggestion approve = new StaffApproveSuggestion();
        	approve.approve(student);
        	//System.out.println(studentid[suggestNo]);
    	}
    }
}
