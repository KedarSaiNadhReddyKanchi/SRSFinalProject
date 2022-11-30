import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller Class
 * @author Hunter Paulson, Junghwan (Kevin) Park, Aidan Morgan
 * CSE360-70605 Final Project
 * <p>
 * The controller manages user interaction and passes these interactions to the view (main/panel) and the model(repository)
 */
public class Controller {
	
	/**
	 * Calls getOpenFile in CSV file chooser
	 * @return String path to the csv file the user wants to open
	 */
	public static String getOpenPath() {
		try {
			return new CSVFileChooser().getOpenFile().getAbsolutePath().toString();
		} catch(java.lang.NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * Calls getSaveFile in CSV file chooser
	 * @return String path where the user wants to save the csv file
	 */
	public static String getSavePath() {
		try {
			return new CSVFileChooser().getSaveFile().getAbsolutePath().toString();
		} catch(java.lang.NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * Calls the load function in Repository
	 * @param filepath path to the csv file being loaded into the roster
	 */
	public static void loadTable(String filepath) {
        Main.repo.load(filepath);
	}
	
	/**
	 * Calls the save function iin Repository
	 * @param filepath path to the csv file that the current roster is being saved to
	 */
    public static void saveTable(String filepath) {
        Main.repo.save(filepath);
    }
	
    /**
     * Calls the datePickerGUI from DatePicker class
     * @param filepath path to the csv file with attendance data for the date being selected
     */
	public static void displayDatePicker(String filepath) {
		// Prompt user to select date of attendance
        DatePicker calendar = new DatePicker();
        calendar.datePickerGUI(filepath);
    }
    
	/**
	 * Calls the addStudentAttendance function from Repository
	 * Calls Display to display the status/result of the added attendance
	 * @param month 
	 * @param day 
	 * @param year
	 * @param filepath path to the csv file with attendance data for the date selected
	 */
	public static void saveDate(int month, int day, int year, String filepath) {
		// Save date and time from file into attendance data

		String[] file_dateStrings = { "20221121", "20221122", "20221123", "20221124", "20221125", "20221126",
				"20221127", "20221128", "20221129" };
		List<LocalDate> dates_from_the_directory = new ArrayList<LocalDate>();

		for (int i = 0; i < file_dateStrings.length; i++) {
			String file_date = file_dateStrings[i];
			int date_year = Integer.parseInt(file_date.substring(0, 4));
			int date_month = Integer.parseInt(file_date.substring(4, 6));
			int date_day = Integer.parseInt(file_date.substring(6));
			LocalDate date = LocalDate.of(date_year, date_month, date_day);
			dates_from_the_directory.add(date);
		}

		// LocalDate date = LocalDate.of(year, month, day);
		// Main.repo.addStudentAttendance(date, filepath);

		for (int i = 0; i < dates_from_the_directory.size(); i++) {
			Main.repo.addStudentAttendance(dates_from_the_directory.get(i), filepath);
		}

		// Display JDialog with added info
		Display dis = new Display();
		dis.displayAttendanceResult(Repository.additionalStudents, Repository.studentsAdded);
	}
    
    /**
     * Calls the Display to display an error when the roster has not been loaded before save, add attendance, and plot data are called
     */
    public static void displayEmptyRosterError() {
    	// Display error for when the roster has not been loaded
    	Display dis = new Display();
    	dis.emptyRosterErrorHandler();
    }
    
    /**
     * Displays the scatter plot GUI
     */
	public static void displayBarPlot() {
		BarPlot.barPlotGUI();
	} 
    
    /**
     * Displays the About dialogue box
     */
    public static void displayAboutTab() {
        Display dis = new Display();
        dis.displayTeamInfo();
    }
}
