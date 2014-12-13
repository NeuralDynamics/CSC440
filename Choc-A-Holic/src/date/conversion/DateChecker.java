package date.conversion;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateChecker {
	private static final int LEAP_STEP = 4;
	
    private Matcher matcher;
    private Pattern pattern;
    
    public static boolean BeforeOrToday(Date d) {	
    	// Get an instance of the calendar & add 1 day to it (for tomorrow)
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, 1);
	    Date tomorrow = calendar.getTime();
	    
	    // Is date before tomorrow? i.e. today or earlier?
	    if (d.before(tomorrow)) { return true; }
	    
	    return false;
    }
    
    public static boolean withinDays(Date d, int numDays) {
    	// Get an instance of the calendar & add 1 day to it (for tomorrow)
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, numDays);
	    Date daysInPast = calendar.getTime();
	    
	    // Is the date within the specified number of days in the past?
	    if (d.after(daysInPast)) { return true; }
	    
	    return false;
    }

    public DateChecker(String regex) {
        pattern = Pattern.compile(regex);
    }

    /**
     * Checks if the date format is a valid.
     * Uses the regex pattern to match the date first. 
     * Than additionally checks are performed on the boundaries of the days taken the month into account (leap years are covered).
     * 
     * @param date the date that needs to be checked.
     * @return if the date is of an valid format or not.
     */
    public boolean check(final String date) {
        matcher = pattern.matcher(date);
        if (matcher.matches()) {
            matcher.reset();
            if (matcher.find()) {
                int day = Integer.parseInt(matcher.group(2));
                int month = Integer.parseInt(matcher.group(1));
                int year = Integer.parseInt(matcher.group(3));

                switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: return day < 32;
                case 4:
                case 6:
                case 9:
                case 11: return day < 31;
                case 2: 
                    int modulo100 = year % 100;
                    //http://science.howstuffworks.com/science-vs-myth/everyday-myths/question50.htm
                    if ((modulo100 == 0 && year % 400 == 0) || (modulo100 != 0 && year % LEAP_STEP == 0)) {
                        //its a leap year
                        return day < 30;
                    } else {
                        return day < 29;
                    }
                default:
                    break;
                }
            }
        }
        return false;
    }

    public String getRegex() {
        return pattern.pattern();
    }
}