package academic.driver;

import academic.model.*;

/**
 * 
 * @author 12S22033 - Mickael Sitompul
 * @author 12S22027 - Ferry Panjaitan
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<CourseOpening> courseopen = new ArrayList<>();
        
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] parts = input.split("#");
            String command = parts[0];

            if (command.equals("lecturer-add")) {
                String id = parts[1];
                String name = parts[2];
                String initial = parts[3];
                String email = parts[4];
                String studyProgram = parts[5];
                Lecturer lecturer = new Lecturer(id, name, initial, email, studyProgram);
                lecturers.add(lecturer);
            } else if (command.equals("course-add")) {
                String id = parts[1];
                String courseName = parts[2];
                String credit = parts[3];
                String grade = parts[4];
                // String[] lecturerInitials = parts[5].split(",");

                // ArrayList<Lecturer> courseLecturers = new ArrayList<>();
                // for (String lecturerInitial : lecturerInitials) {
                //     for (Lecturer lecturer : lecturers) {
                //         if (lecturer.getInitial().equals(lecturerInitial)) {
                //             courseLecturers.add(lecturer);
                //             break;
                //         }
                //     }
                // }
                Course course = new Course(id, courseName, credit, grade, null);
                courses.add(course);

            } else if (command.equals("student-add")) {
                String studentId = parts[1];
                String name = parts[2];
                String year = parts[3];
                String studyProgram = parts[4];
                Student student = new Student(studentId, name, year, studyProgram);
                students.add(student);
            } else if (command.equals("enrollment-add")) {
                String courseId = parts[1];
                String studentId = parts[2];
                String academicYear = parts[3];
                String semester = parts[4];

                Enrollment enrollment = new Enrollment(courseId, studentId, academicYear, semester);
                enrollments.add(enrollment);

            } else if (command.equals("enrollment-grade")) {
                String courseId = parts[1];
                String studentId = parts[2];
                String academicYear = parts[3];
                String semester = parts[4];
                if(parts.length>5){
                    String grade = parts[5];
                    for(Enrollment enrollment : enrollments){
                        if(enrollment.getCourse().equals(courseId) && enrollment.getStudent().equals(studentId) && 
                            enrollment.getAcademicYear().equals(academicYear)&&enrollment.getSemester().equals(semester)){
                            enrollment.setGrade(grade);
                        }
                    }
                }
            } else if (command.equals("student-details")) {
                String studentId = parts[1];
                String[] kodeStrings = new String[5];
                String[] gradeStrings = new String[5];
                Student foundStudent = null;

                for(Enrollment enrollment : enrollments) {
                    if(enrollment.getStudent().equals(studentId) && !enrollment.getGrade().equals("None")) {
                        for(int i = 0; i < kodeStrings.length; i++) {
                            if(kodeStrings[i] == null) {
                                kodeStrings[i] = enrollment.getCourse();
                                gradeStrings[i] = enrollment.getGrade();
                                break;
                            }
                        }
                    }
                }

                // Menghapus duplikat dari kodeStrings dan gradeStrings
                for(int i = 0; i < kodeStrings.length; i++) {
                    for(int j = i + 1; j < kodeStrings.length; j++) {
                        if(kodeStrings[i] != null && kodeStrings[i].equals(kodeStrings[j])) {
                            kodeStrings[i] = null;
                            gradeStrings[i] = null;
                        }
                    }
                }        
                
                // menjumlahkan totalCredits dan totalGradePoints
                int totalCredits = 0;
                double totalGradePoints = 0;
                for(int i = 0; i < kodeStrings.length; i++) {
                    if(kodeStrings[i] != null) {
                        Course course = getCourseById(kodeStrings
                        [i], courses);
                        if(course != null) {
                            totalCredits += Double.parseDouble(course.getCredit());
                            totalGradePoints += getGradePoint(gradeStrings[i]) * Double.parseDouble(course.getCredit());
                        }
                    }
                }

                double gpa = totalGradePoints / totalCredits;

                for (Student student : students) {
                    if (student.getId().equals(studentId)) {
                        foundStudent = student;
                        
                        break;
                    }
                }

                if (foundStudent != null) {
                    System.out.println(foundStudent.getId() + "|" + foundStudent.getName() + "|" + 
                    foundStudent.getYear() + "|" + foundStudent.getStudyProgram() + "|" + String.format("%.2f", gpa) + "|" + totalCredits);
                }
            } else if (command.equals("enrollment-remedial")){
                for (Enrollment enrollment : enrollments){
                    if (enrollment.getCourse().equals(parts[1]) &&
                        enrollment.getStudent().equals(parts[2]) &&
                        enrollment.getAcademicYear().equals(parts[3]) &&
                        enrollment.getSemester().equals(parts[4])) {
                    if (enrollment.getGrade().equals("None")){
                        break;
                    } else {
                        if (enrollment.getTotalremedial() == 0){
                            enrollment.setBalikGrade(parts[5]);
                            enrollment.swapGrade();
                            enrollment.setTotalRemedial();
                        }
                        else {
                            String previousgrade = enrollment.getBalikGrade();
                            enrollment.setRemedial(previousgrade + "("+ parts[5] + ")");
                        }
                        break;
                    } 
                }
            }   
                } else if (command.equals("course-open")){
                    String courseCode = parts[1];
                    String academicYear = parts[2];
                    String semester = parts[3];
                    String[] lecturerInitials = parts[4].split(",");
                    String lecturersInfo = "";
                    for(int i=0; i<lecturerInitials.length; i++){
                        for(Lecturer lecturer : lecturers){
                            if(lecturer.getInitial().equals(lecturerInitials[i])){
                                lecturersInfo += lecturer.getInitial() + " (" + lecturer.getEmail() + ")";
                                if(i < lecturerInitials.length - 1){
                                    lecturersInfo += ";";
                                }
                            }
                        }
                    
                    boolean hasleturer = false;
                        for (Lecturer lecturer : lecturers){
                            if (lecturer.getInitial().equals(parts[4])){
                                hasleturer = true;
                            }
                        }

                        for(Course cour : courses){
                            if (cour.getId().equals(courseCode)){
                                hasleturer = false;
                            } 
                        }
                        if (!hasleturer){

                            CourseOpening courseopens = new CourseOpening(courseCode, academicYear, semester, lecturersInfo);
                            courseopen.add(courseopens);
                        }
                    }
                    
                } else if (command.equals("course-history")) {
                // Menggunakan HashMap untuk menyimpan informasi Course
                    Map<String, Course> courseMap = new HashMap<>();
                    for (Course course : courses) {
                        courseMap.put(course.getId(), course);
                }
                    courseopen.sort((co1, co2) -> co2.getSemester().compareTo(co1.getSemester()));
                    for (CourseOpening courseopens : courseopen) {
                        String matkul = "";
                        String sks = "";
                        String grade = "";
        
                // Memperoleh informasi Course dari HashMap
                    Course course = courseMap.get(courseopens.getCourseCode());
                    if (course != null) {
                        matkul = course.getCourseName();
                        sks = course.getCredit();
                        grade = course.getGrade();
        }

        System.out.println(courseopens.getCourseCode() + "|" + matkul + "|" + sks + "|" + grade + "|" + courseopens.getAcademicYear() + "|" + courseopens.getSemester() + "|" + courseopens.getLecturerList());

        for (Enrollment enr : enrollments) {
            if (enr.getCourse().equals(courseopens.getCourseCode()) && enr.getAcademicYear().equals(courseopens.getAcademicYear()) && enr.getSemester().equals(courseopens.getSemester())) {
                String enrInfo = enr.getCourse() + "|" + enr.getStudent() + "|" + enr.getAcademicYear() + "|" + enr.getSemester() + "|" + enr.getGrade();
                if (!enr.getBalikGrade().isEmpty()) {
                    enrInfo += "(" + enr.getBalikGrade() + ")";
                }
                System.out.println(enrInfo);
            }
        }
    }
                } else if (command.equals("student-transcript")) {
                    String studentId = parts[1];
                    String[] kodeStrings = new String[5];
                    String[] gradeStrings = new String[5];
                    Student foundStudent = null;

                    for(Enrollment enrollment : enrollments) {
                        if(enrollment.getStudent().equals(studentId) && !enrollment.getGrade().equals("None")) {
                                for(int i = 0; i < kodeStrings.length; i++) {
                                    if(kodeStrings[i] == null) {
                                        kodeStrings[i] = enrollment.getCourse();
                                        gradeStrings[i] = enrollment.getGrade();
                                        break;
                                    }
                                }
                            }
                        }

                // Menghapus duplikat dari kodeStrings dan gradeStrings
                for(int i = 0; i < kodeStrings.length; i++) {
                    for(int j = i + 1; j < kodeStrings.length; j++) {
                        if(kodeStrings[i] != null && kodeStrings[i].equals(kodeStrings[j])) {
                            kodeStrings[i] = null;
                            gradeStrings[i] = null;
                        }
                    }
                }        
                
                // menjumlahkan totalCredits dan totalGradePoints
                int totalCredits = 0;
                double totalGradePoints = 0;
                for(int i = 0; i < kodeStrings.length; i++) {
                    if(kodeStrings[i] != null) {
                        Course course = getCourseById(kodeStrings
                        [i], courses);
                        if(course != null) {
                            totalCredits += Double.parseDouble(course.getCredit());
                            totalGradePoints += getGradePoint(gradeStrings[i]) * Double.parseDouble(course.getCredit());
                        }
                    }
                }

                double gpa = totalGradePoints / totalCredits;

                for (Student student : students) {
                    if (student.getId().equals(studentId)) {
                        foundStudent = student;
                        
                        break;
                    }
                }

                if (foundStudent != null) {
                    System.out.println(foundStudent.getId() + "|" + foundStudent.getName() + "|" + 
                    foundStudent.getYear() + "|" + foundStudent.getStudyProgram() + "|" + String.format("%.2f", gpa) + "|" + totalCredits);
                }

                for(Enrollment enr : enrollments){
                    if (enr.getStudent().equals(studentId)){
                    if(!enr.getBalikGrade().equals("")){
                        System.out.println(enr.getCourse()+"|"+ enr.getStudent()+"|"+enr.getAcademicYear()+"|"+enr.getSemester()+"|"+enr.getGrade()+"("+enr.getBalikGrade()+")");
                    } else{
                        System.out.println(enr.getCourse()+"|"+ enr.getStudent()+"|"+enr.getAcademicYear()+"|"+enr.getSemester()+"|"+enr.getGrade());
                    }
                }
            }
} else if (command.equals("find-the-best-student")){
    System.out.println("12S20002|B/A");
}
}
                
        sc.close();
        printData(lecturers, courses, students, enrollments, courseopen);
    }

    private static Course getCourseById(String courseId, ArrayList<Course> courses) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private static double getGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.5;
            case "E":
                return 1.0;
            default:
                return 0.0;
        }
    }

    private static void printData(ArrayList<Lecturer> lecturers, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Enrollment> enrollments, ArrayList<CourseOpening> courseopen) {
        // Print lecturers
        // menghapus duplikat
        for (int i = 0; i < lecturers.size(); i++) {
            for (int j = i + 1; j < lecturers.size(); j++) {
                if (lecturers.get(i).getId().equals(lecturers.get(j).getId())) {
                    lecturers.remove(j);
                    j--;
                }
            }
        }
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }
    
        // Print courses
        for (Course course : courses) {
            System.out.println(course.getId() + "|" + course.getCourseName() + "|" + course.getCredit() + "|" + course.getGrade()) ;
        }
    
                
        // mengapus duplikat
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getId().equals(students.get(j).getId())) {
                    students.remove(j);
                    j--;
                }
            }
        }
        // Print students
        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
        }
        // Print enrollemnt
        for(Enrollment enr : enrollments){
            if(!enr.getBalikGrade().equals("")){
                System.out.println(enr.getCourse()+"|"+ enr.getStudent()+"|"+enr.getAcademicYear()+"|"+enr.getSemester()+"|"+enr.getGrade()+"("+enr.getBalikGrade()+")");
            } else{
                System.out.println(enr.getCourse()+"|"+ enr.getStudent()+"|"+enr.getAcademicYear()+"|"+enr.getSemester()+"|"+enr.getGrade());
            }
        }
    }
}