import java.util.Scanner;



public class Camp {
    

    Scanner scan = new Scanner(System.in);
    private String    campName;
    private String    startDate;
    private String    endDate;
    private String    registrationClosingDate;   //YYYY_MM_DD
    private String    userGroup;                 //faculties or NTU
    private String    location;
    private int       totalSlots;
    private int       campCommitteeSlots;
    private String    description;
    private String    staffUserID;


    public boolean   isVisible;


    public Camp(String campName, String startDate, String endDate, String registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, String staffUserID)
    {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots =totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffUserID = staffUserID;
        this.isVisible = true;

    }


    public Camp(String campName, String startDate, String endDate, String registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, String staffUserID, boolean isVisible)
    {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots =totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffUserID = staffUserID;
        this.isVisible = isVisible;

    }
    public void showCampInfo(){
        System.out.println("\nThis is the current camp information");
        System.out.println("1. Camp Name:                " + campName);
        System.out.println("2. Start Date:               " + startDate);
        System.out.println("3. End Date:                 " + endDate);
        System.out.println("4. Registration Cloing Date: " + registrationClosingDate);
        System.out.println("5. User Group:               " + userGroup);
        System.out.println("6. Location of Camp:         " + location);
        System.out.println("7. Total slots:              " + totalSlots);
        System.out.println("8. Total committee slots:    " + campCommitteeSlots);
        System.out.println("9. Description of Camp:      " + description);
        System.out.println("10. Visibility of camp:      " + isVisible);
    }





//-----------Getters and Setters------------------------------------------


    public String getCampName(){
        return campName;
    }

    public void setCampName(){
        System.out.println("Please input a new Name for your Camp");
        String newCampName = scan.nextLine();
        campName = newCampName;
        System.out.println("New camp name is " + campName +"\n");
    }

    public String getStartDate(){
        return startDate;
    }

    public void setStartDate(){
        System.out.println("Please input a new start date for your camp in the format YYYY_MM_DD");
        String newStartDate = scan.nextLine();
        while (!DateChecker.isValidDate(newStartDate)) {
            System.out.println("Date entered in wrong format! Please re-enter start date in YYYY_MM_DD format.");
            newStartDate = scan.nextLine();
        }
        startDate = newStartDate;
        System.out.println("New start date is " + startDate +"\n");
    }

     public String getEndDate(){
        return endDate;
    }

    public void setEndDate(){
        System.out.println("Please input a new end date for your camp in the format YYYY_MM_DD");
        String newEndDate = scan.nextLine();
        while (!DateChecker.isValidDate(newEndDate)) {
            System.out.println("Date entered in wrong format! Please re-enter end date in YYYY_MM_DD format.");
            newEndDate = scan.nextLine();
        }
        while (DateChecker.isFirstDateBeforeSecondDate(newEndDate, startDate)) {
            System.out.println("End date of camp cannot be before start date! Please re-enter end date in YYYY_MM_DD format.");
            newEndDate = scan.nextLine();
            while (!DateChecker.isValidDate(newEndDate)) {
            System.out.println("Date entered in wrong format! Please re-enter end date in YYYY_MM_DD format.");
            newEndDate = scan.nextLine();
            }
        }
        endDate = newEndDate;
        System.out.println("New end date is" + endDate +"\n");
    }

    public String getRegistrationClosingDate(){
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(){
        System.out.println("Please input a new closing date for registration for your camp in the format YYYY_MM_DD");
        String newRegistrationClosingDate = scan.nextLine();
        while (!DateChecker.isValidDate(newRegistrationClosingDate)) {
            System.out.println("Date entered in wrong format! Please re-enter closing date for registration in YYYY_MM_DD format.");
            newRegistrationClosingDate = scan.nextLine();
        }
        while (DateChecker.isFirstDateBeforeSecondDate(startDate, newRegistrationClosingDate)) {
            System.out.println("Registration closing date of camp cannot be after start date! Please re-enter registration closing date in YYYY_MM_DD format.");
            newRegistrationClosingDate = scan.nextLine();
            while (!DateChecker.isValidDate(newRegistrationClosingDate)) {
            System.out.println("Date entered in wrong format! Please re-enter closing date for registration in YYYY_MM_DD format.");
            newRegistrationClosingDate = scan.nextLine();
            }
        }
        registrationClosingDate = newRegistrationClosingDate;
        System.out.println("New registration closing date is" + registrationClosingDate +"\n");
    }

    public String getUserGroup(){
        return userGroup;
    }
    public void setUserGroup(){
        System.out.println("Please enter a new user group for your camp");
        String newUserGroup = scan.nextLine();
        userGroup = newUserGroup;
        System.out.println("New user group is "+userGroup +"\n");
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(){
        System.out.println("Please enter a new location for your camp");
        String newLocation = scan.nextLine();
        location = newLocation;
        System.out.println("New location is "+location +"\n");
    }

    public int getTotalSlots(){
        return totalSlots;
    }

    public void setTotalSlots(){
        System.out.println("Please enter the new number of slots for your camp");
        int newTotalSlots = scan.nextInt(); scan.nextLine();
        totalSlots = newTotalSlots;
        System.out.println("New total camp slots is " + totalSlots +"\n");
    }

    public int getCampCommitteeSlots(){
        return campCommitteeSlots;
    }

    public void setCampCommitteeSlots(){
        System.out.println("Please enter the new number of slots available for the camp committee");
        int newCampCommitteeSlots = scan.nextInt(); scan.nextLine();
        while(newCampCommitteeSlots > 10){
            System.out.println("Number of camp committee members cannot be more than 10! Please re-enter.");
            newCampCommitteeSlots = scan.nextInt(); scan.nextLine();
        }
        campCommitteeSlots = newCampCommitteeSlots;
        System.out.println("New total Camp Committee Slots is " + campCommitteeSlots +"\n");
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        System.out.println("Please enter a new description for your camp");
        String newDescription = scan.nextLine();
        description = newDescription;
        System.out.println("New description is: "+description +"\n");
    }



    public boolean getIsVisible(){
        return isVisible;
    } 

    public void toggleVisibility(){
        System.out.println("Toggling the visibility for your camp!");
        isVisible = !isVisible;
        if(isVisible)
            System.out.println("Camp is visible to the user group now\n");
        else 
            System.out.println("Camp is not visible to the user group now\n");
    }

    public String getStaffID() {
        return staffUserID;
    }

    public void decreaseCampSlots(){
        this.totalSlots--;

    }

    public void increaseCampSlots(){
        this.totalSlots++;

    }

    public void decreaseCampCommitteeSlots(){
        this.campCommitteeSlots--;
    }


}
