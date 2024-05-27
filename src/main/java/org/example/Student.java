package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1; // Default nextId starts from 1

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = generateStudentId();
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    private static String generateStudentId() {
        return "S" + String.format("%06d", nextId++);
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);
        return true;
    }

    public boolean dropCourse(String courseId) {
        for (Course course : registeredCourses) {
            if (course.getCourseId().equals(courseId)) {
                registeredCourses.remove(course);
                course.getRegisteredStudents().remove(this);
                return true;
            }
        }
        return false;
    }

    public String toSimplifiedString() {
        return String.format("Student ID: %s, Name: %s, Department: %s",
                studentId, studentName, department.getDepartmentName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Student ID: %s, Name: %s, Gender: %s, Address: %s, Department: %s\n",
                studentId, studentName, gender, address, department));
        sb.append("Registered Courses:\n");
        for (Course course : registeredCourses) {
            sb.append(String.format("  Course ID: %s, Name: %s, Department: %s\n",
                    course.getCourseId(), course.getCourseName(), course.getDepartment().getDepartmentName()));
        }
        return sb.toString();
    }
}
