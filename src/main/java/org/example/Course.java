package org.example;

import lombok.*;

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
        finalScores.add(null); // Add a new null element for the finalScores
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null); // Add a new null score for the student in each assignment
        }
        return true;
    }

    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            double weightedTotal = 0;
            for (Assignment assignment : assignments) {
                Double score = Double.valueOf(assignment.getScores().get(i));
                weightedTotal += score * assignment.getWeight();
            }
            averages[i] = (int) Math.round(weightedTotal);
        }
        return averages;
    }

    public void addAssignment(String assignmentName, double weight, int maxScore) {
        if (isAssignmentWeightValid()) {
            return; // Cannot add more assignments as total weight is already 1
        }
        assignments.add(new Assignment(assignmentName, weight, maxScore));
        for (int i = 0; i < registeredStudents.size(); i++) {
            assignments.getLast().getScores().add(null); // Add a new null score for each student
        }
    }

    public void generateScores() {
        // Ensure there are registered students and assignments before generating scores
        if (registeredStudents.isEmpty() || assignments.isEmpty()) {
            System.out.println("No registered students or assignments. Unable to generate scores.");
            return;
        }

        // Generate random scores for each assignment and student
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            for (Assignment assignment : assignments) {
                assignment.generateRandomScore(); // Generate a random score for each assignment
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    assignment.getScores().add(score);
                }
            }
        }

        // Calculate final scores for each student
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            double finalScore = calculateFinalScore(student);
            finalScores.add(finalScore);
        }
    }

    public double calculateFinalScore(Student student) {
        for (int i = 0; i < registeredStudents.size(); i++) {
            double finalScore = 0.0;
            for (int j = 0; j < assignments.size(); j++) {
                double assignmentScore = assignments.get(j).getScores().get(i); // Getting the score for the current student
                double weight = assignments.get(j).getWeight();
                finalScore += (assignmentScore / assignments.get(j).getMaxScore()) * weight;
            }
            finalScores.add(finalScore); // Adding the final score for the current student
        }
        return 0;
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
                ArrayList<Integer> scores = assignment.getScores();
                Double score = Double.valueOf(scores != null && i < scores.size() ? scores.get(i) : null);
                System.out.printf("%-15s", score != null ? score.intValue() : "N/A");
            }
            System.out.printf("%-15.2f", finalScores.get(i)); // Assuming finalScores is a list of doubles
            System.out.println();
        }
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

    public int[] calcStudentAvg() {
        int[] avgScores = new int[registeredStudents.size()];

        // Calculate the average score for each student
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            double totalScore = 0;
            int totalAssignments = assignments.size();

            // Sum up the scores of all assignments for the student
            for (Assignment assignment : assignments) {
                int index = assignments.indexOf(assignment);
                double score = assignment.getScores().get(i); // Get the score for the current student
                totalScore += score;
            }

            // Calculate the average score for the student
            double avgScore = totalScore / totalAssignments;
            avgScores[i] = (int) Math.round(avgScore); // Round the average score to the nearest integer
        }

        return avgScores;
    }
}
