package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;
    private List<Double> finalScores;
    private static int nextId = 1; // Default nextId starts from 1

    public Course(String courseName, double credits, Department department) {
        this.courseId = generateCourseId(department);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    private static String generateCourseId(Department department) {
        return "C-" + department.getDepartmentId() + "-" + String.format("%02d", nextId++);
    }

    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0; // Check if the total weight equals 100%
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false; // Student already registered
        }
        registeredStudents.add(student);
        finalScores.add(0.0); // Add a new 0.0 element for the finalScores
        for (Assignment assignment : assignments) {
            assignment.getScores().add(0); // Add a new 0 score for the student in each assignment
        }
        return true;
    }

    public void addAssignment(String assignmentName, double weight, int maxScore) {
        if (isAssignmentWeightValid()) {
            return; // Cannot add more assignments as total weight is already 1
        }
        assignments.add(new Assignment(assignmentName, weight, maxScore));
        for (int i = 0; i < registeredStudents.size(); i++) {
            assignments.getLast().getScores().add(0); // Add a new 0 score for each student
        }
    }

    public void generateScores() {
        // Ensure there are registered students and assignments before generating scores
        if (registeredStudents.isEmpty() || assignments.isEmpty()) {
            System.out.println("No registered students or assignments. Unable to generate scores.");
            return;
        }

        // Generate random scores for each assignment and student
        for (Assignment assignment : assignments) {
            for (int i = 0; i < registeredStudents.size(); i++) {
                int randomScore = (int) (Math.random() * assignment.getMaxScore() + 1); // Generate a random score
                assignment.getScores().set(i, randomScore); // Set the random score for the student
            }
        }

        // Calculate final scores for each student
        calculateFinalScores();
    }

    public void calculateFinalScores() {
        for (int i = 0; i < registeredStudents.size(); i++) {
            double finalScore = 0.0;
            for (Assignment assignment : assignments) {
                if (assignment.getScores().get(i) != null) {
                    double score = assignment.getScores().get(i);
                    double weight = assignment.getWeight();
                    finalScore += (score / assignment.getMaxScore()) * weight * 100;
                }
            }
            finalScores.set(i, finalScore); // Set the final score for the student
        }
    }

    public double[] calcStudentsAverage() {
        double[] averages = new double[assignments.size()]; // Not including final score
        int studentCount = registeredStudents.size();

        for (int j = 0; j < assignments.size(); j++) {
            double total = 0;
            for (int i = 0; i < studentCount; i++) {
                total += assignments.get(j).getScores().get(i);
            }
            averages[j] = total / studentCount;
        }

        return averages;
    }

    public void displayScores() {
        // Print header
        System.out.printf("%-20s", "Student");
        for (Assignment assignment : assignments) {
            System.out.printf("%-15s", assignment.getAssignmentName());
        }
        System.out.printf("%-15s", "Final Score");
        System.out.println();

        // Print student scores
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%-20s", student.getStudentName());
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%-15s", score != null ? score : "N/A");
            }
            System.out.printf("%-15.2f", finalScores.get(i) != null ? finalScores.get(i) : 0.0);
            System.out.println();
        }

        // Calculate and print average scores for each assignment
        double[] averages = calcStudentsAverage();
        System.out.printf("%-20s", "Average");
        for (int j = 0; j < assignments.size(); j++) {
            System.out.printf("%-15.2f", averages[j]);
        }
        System.out.printf("%-15.2f", finalScores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0)); // Final score average
        System.out.println();
    }

    public String toSimplifiedString() {
        return String.format("Course ID: %s, Name: %s, Credits: %.2f, Department: %s",
                courseId, courseName, credits, department.getDepartmentName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Course ID: %s, Name: %s, Credits: %.2f, Department: %s\n",
                courseId, courseName, credits, department.getDepartmentName()));
        sb.append("Assignments:\n");
        for (Assignment assignment : assignments) {
            sb.append(String.format("  Assignment: %s, Weight: %.2f, Max Score: %d\n",
                    assignment.getAssignmentName(), assignment.getWeight(), assignment.getMaxScore()));
        }
        sb.append("Registered Students:\n");
        for (Student student : registeredStudents) {
            sb.append(String.format("  Student ID: %s, Name: %s, Department: %s\n",
                    student.getStudentId(), student.getStudentName(), student.getDepartment().getDepartmentName()));
        }
        return sb.toString();
    }
}
