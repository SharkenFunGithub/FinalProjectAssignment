import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        // Initialize a course
        Department department = new Department("D00", "Computer Science");
        course = new Course("Programming 1", 3.0, department);

        // Create students
        Address address1 = new Address(1, "Street 1", "City", "Province", "123456", "Country");
        student1 = new Student("John Doe", Gender.MALE, address1, department);
        Address address2 = new Address(2, "Street 2", "City", "Province", "654321", "Country");
        student2 = new Student("Jane Smith", Gender.FEMALE, address2, department);

        // Register students in the course
        course.registerStudent(student1);
        course.registerStudent(student2);

        // Add assignments and generate random scores
        course.addAssignment("Assignment 01", 0.3, 100);
        course.addAssignment("Assignment 02", 0.3, 100);
        course.addAssignment("Exam", 0.4, 100);
        course.generateScores();
    }

    @Test
    public void testCalcStudentAvg() {
        // Calculate the student average
        int[] avgScores = course.calcStudentAvg();

        // Assert that the average scores array is not null
        assertNotNull(avgScores);

        // Assert that the average scores array has the expected length (number of students)
        assertEquals(2, avgScores.length);

        // Assert that the calculated average scores are within a reasonable range
        // (e.g., between 0 and the maximum possible score)
        for (int score : avgScores) {
            assertTrue(score >= 0 && score <= 100);
        }
    }
}