import org.example.Assignment;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class AssignmentTest {

    @Test
    public void testCalcAssignmentAvg() {
        Assignment assignment = new Assignment("Assignment 1", 0.3, 100);
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(90);
        scores.add(95);
        assignment.setScores(scores);
        assignment.calcAssignmentAvg();
        assertEquals(88.33, assignment.getAssignmentAverage(), 0.01);
    }

    @Test
    public void testIsAssignmentsTotalWeightValid1() {
        Assignment assignment1 = new Assignment("Assignment 1", 0.5, 100);
        Assignment assignment2 = new Assignment("Assignment 2", 0.5, 100);
        assertTrue(Assignment.isAssignmentsTotalWeightValid(assignment1, assignment2));
    }

    @Test
    public void testIsAssignmentsTotalWeightValid2() {
        Assignment assignment1 = new Assignment("Assignment 1", 0.3, 100);
        Assignment assignment2 = new Assignment("Assignment 2", 0.6, 100);
        assertFalse(Assignment.isAssignmentsTotalWeightValid(assignment1, assignment2));
    }
}
