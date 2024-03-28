import java.util.Scanner;

public class Suggestion {
	Scanner scan = new Scanner(System.in);
	private String campName;
	private String staffUserID;
	private String[] studentUserID = new String [10];
	private String[] suggestion = new String [10];
	
	public Suggestion(String campName, String staffUserID, String[] studentUserID, String[] suggestion) {
		this.campName = campName;
		this.staffUserID = staffUserID;
		this.studentUserID = studentUserID;
		this.suggestion = suggestion;
	}
	
	public void setSuggestion(int i) {
		System.out.println("Please enter your suggestion: ");
		System.out.println("----------------------------------");
	    suggestion[i] = scan.nextLine();

		System.out.println("\nSuggestion successfuly submitted!");
		System.out.println("Returning to the previous page...");
	}
	public void setSuggestionNull(int i) {
	    suggestion[i] = "-";
	}
	public void setSuggestionApprove(int i) {
		suggestion[i] = "Approved";
	}
	public void setStudentID(int i, String s) {
		studentUserID[i] = s;
	}
	
	public String getCampName() {
		return campName;
	}
	public String getStaffUserID() {
		return staffUserID;
	}
	public String getSuggestion(int i) {
		return suggestion[i];
	}
	public String getStudentID(int i) {
		return studentUserID[i];
	}
	
}
