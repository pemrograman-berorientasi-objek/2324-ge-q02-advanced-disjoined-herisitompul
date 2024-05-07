package academic.model;


/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */

public class Enrollment {
    private final String course;
    private String student;
    private final String academicYear;
    private final String semester;
    private String grade;
    private String balikGrades;
    private String remedial;
    private int totalremedial;


    public Enrollment(String _course, String _student, String _academicYear, String _semester) {
        this.course = _course;
        this.student = _student;
        this.academicYear = _academicYear;
        this.semester = _semester;
        this.grade = "None";
        this.balikGrades = "";
        this.remedial = null;
        this.totalremedial = 0;

    }

    public String getCourse() {
        return course;
    }

    public String getStudent() {
        return student;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }
    
    public String getBalikGrade() {
        return this.balikGrades;
    }

    public String getGrade() {
        return grade;
    }

    public String getRemedial() {
        return remedial;
    }

    public void setRemedial(String remedial) {
        this.remedial = remedial;
    }

    public int getTotalremedial() {
        return this.totalremedial;
    }

    public void setTotalRemedial(){
        this.totalremedial += 1;
    }

    public void swapGrade(){
        String temp = "";
        temp = this.grade;
        this.grade = this.balikGrades;
        this.balikGrades = temp;
    }
    

    public void setGrade(String grade) {
        this.grade = grade;
}

public void setBalikGrade(String _BalikGrade) {
    this.balikGrades = _BalikGrade;
}

}
