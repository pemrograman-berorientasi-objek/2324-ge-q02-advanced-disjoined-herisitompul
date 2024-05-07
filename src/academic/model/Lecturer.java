package academic.model;
/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */

public class Lecturer extends Human{
    private String initial;
    private String email;
    

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        super (studyProgram, name, id);
        
        this.initial = initial;
        this.email = email;
        
    }
    public String getInitial() {
        return initial;
    }

    public String getEmail() {
        return email;
    }
}
