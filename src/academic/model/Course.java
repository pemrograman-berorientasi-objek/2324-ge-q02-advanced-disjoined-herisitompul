package academic.model;

import java.util.ArrayList;
/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */
public class Course {
    private String id;
    private String courseName;
    private String credit;
    private String grade;
    private ArrayList<Lecturer> lecturers;

    public Course(String id, String courseName, String credit, String grade, ArrayList<Lecturer> lecturers) {
        this.id = id;
        this.courseName = courseName;
        this.credit = credit;
        this.grade = grade;
        this.lecturers = lecturers;
    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    @Override
    public String toString() {
        StringBuilder lecturersInfo = new StringBuilder();
        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer lecturer = lecturers.get(i);
            lecturersInfo.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
            if (i < lecturers.size() - 1) {
                lecturersInfo.append(";");
            }
        }
        return id + "|" + courseName + "|" + credit + "|" + grade + "|" + lecturersInfo.toString();
    }

}
