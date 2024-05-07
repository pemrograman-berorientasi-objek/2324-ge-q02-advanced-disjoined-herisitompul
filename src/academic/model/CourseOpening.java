package academic.model;

/**
 * @author 12S22033 Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 * 
 */
public class CourseOpening {

    // course-open#<course-code>#<academic-year>#<semester>#<lecturer-list>
    private String courseCode;
    private String academicYear;
    private String semester;
    private String lecturerList;

    public CourseOpening(String courseCode, String academicYear, String semester, String lecturerList) {
        this.courseCode = courseCode;
        this.academicYear = academicYear;
        this.semester = semester;
        this.lecturerList = lecturerList;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getLecturerList() {
        return lecturerList;
    }
    

}