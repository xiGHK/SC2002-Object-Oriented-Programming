import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateChecker {
     public static boolean beforeRegistrationDeadline(Camp camp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate registrationClosing = LocalDate.parse(camp.getRegistrationClosingDate(), formatter);

        LocalDate currentDate = LocalDate.now();

        return currentDate.isBefore(registrationClosing);
    }

    public static boolean isValidDate(String date) {
        if (date.length() != 10) {
            return false;
        }

        if (date.charAt(4) != '_' || date.charAt(7) != '_') {
            return false;
        }

        try {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));

            if (year < 1000 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFirstDateBeforeSecondDate(String firstDate, String secondDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate first = LocalDate.parse(firstDate, formatter);
        LocalDate second = LocalDate.parse(secondDate, formatter);

        return first.isBefore(second);
    }

    public static boolean isDateClashPresent(Camp camp, Student student, StudentCampsViewer campViewer){
        //get all the camps student is part of
        StudentRegisteredCamps registeredCamps = new StudentRegisteredCamps(student);
        List<String>attendees = registeredCamps.getCampAttendees();
        attendees.addAll(registeredCamps.getCampCommittee());

        List<Camp> allCamps = campViewer.getCreatedCamps();

        for ( String camps : attendees){
            for(Camp allcamps: allCamps){
                if ( allcamps.getCampName().equals(camps) ){
                    if (isCampsDateClash(allcamps, camp)) {
                        return true;
                    }
                }
             //   updatedCamps.add(camp);
            }
        }

        return false;
    }

    public static boolean isCampsDateClash(Camp camp1, Camp camp2){
        if (isFirstDateBeforeSecondDate(camp1.getStartDate(),camp2.getEndDate()) 
        && isFirstDateBeforeSecondDate(camp2.getStartDate(), camp1.getStartDate())){
            return true;
        }else if (isFirstDateBeforeSecondDate(camp1.getEndDate(),camp2.getEndDate()) 
        && isFirstDateBeforeSecondDate(camp2.getStartDate(), camp1.getEndDate())){
            return true;
        }else if (isFirstDateBeforeSecondDate(camp2.getStartDate(),camp1.getEndDate()) 
        && isFirstDateBeforeSecondDate(camp1.getStartDate(), camp2.getStartDate())){
            return true;
        }else if (isFirstDateBeforeSecondDate(camp2.getEndDate(),camp1.getEndDate()) 
        && isFirstDateBeforeSecondDate(camp1.getStartDate(), camp2.getEndDate())){
            return true;
        }else if (isFirstDateBeforeSecondDate(camp1.getStartDate(), camp2.getStartDate())
        && isFirstDateBeforeSecondDate(camp2.getEndDate(), camp1.getEndDate())){
            return true;
        }else if (isFirstDateBeforeSecondDate(camp2.getStartDate(), camp1.getStartDate())
        && isFirstDateBeforeSecondDate(camp1.getEndDate(), camp2.getEndDate())){
            return true;
        }else {return false;}

        
    }
}
