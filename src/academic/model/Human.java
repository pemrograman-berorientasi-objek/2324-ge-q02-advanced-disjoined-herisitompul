package academic.model;

/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */


public class Human {
    private String studyProgram;
    private String name;
    private String id;


    public Human (String studyProgram, String name, String id) {
        this.studyProgram = studyProgram;
        this.name = name;
        this.id = id;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
 
}

