package academic.model;

/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */

public class Student extends Human {
    private String year;

    public Student(String id, String name, String year, String studyProgram) {
        super (studyProgram, name, id);
        this.year = year; 

    }
    
    public String getYear() {
        return year;
    }

}