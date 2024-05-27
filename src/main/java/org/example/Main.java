package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double weightNumberAss = 0.1;
        double weightNumberExam = 0.4;
        int maxNumber = 100;

        // Create a department
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        Department department = new Department(departmentName);

        // Create a course
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter credits: ");
        double credits = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Course course = new Course(courseName, credits, department);

        // Create an address
        System.out.print("Enter street number: ");
        int streetNo = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter street name: ");
        String street = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter province: ");
        String province = scanner.nextLine();
        System.out.print("Enter postal code: ");
        String postalCode = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        Address address = new Address(streetNo, street, city, province, postalCode, country);

        // Create a student
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
        Student student = new Student(studentName, gender, address, department);

        // Register the student in the course
        course.registerStudent(student);

        // Register the student in the course
        boolean success = student.registerCourse(course);
        if (success) {
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Student is already registered in the course.");
        }

        // Drop the course for the student
        boolean dropped = student.dropCourse(course.getCourseId());
        if (dropped) {
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("Student is not registered in the course.");
        }


        // Add assignments
        course.addAssignment("Assignment 01", weightNumberAss, maxNumber);
        course.addAssignment("Assignment 02", weightNumberAss, maxNumber);
        course.addAssignment("Assignment 03", weightNumberAss, maxNumber);
        course.addAssignment("Exam01", weightNumberExam, maxNumber);
        course.addAssignment("Exam02", weightNumberExam, maxNumber);

        // Generate scores
        course.generateScores();

        // Display scores three times
        for (int i = 0; i < 3; i++) {
            // Generate random scores
            course.generateScores();

            // Display scores
            course.displayScores();
            System.out.println(); // Add a newline between each set of scores
        }


        // Display simplified course information
        System.out.println(course.toSimplifiedString());

        // Display
        System.out.println(student.toSimplifiedString());

        scanner.close();
    }
}
