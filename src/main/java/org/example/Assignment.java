package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private double assignmentAverage;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight, int maxScore) {
        this.assignmentId = "A-" + String.format("%02d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.maxScore = maxScore;
        this.scores = new ArrayList<>();
    }

    public void calcAssignmentAvg() {
        if (scores.isEmpty()) {
            assignmentAverage = 0;
            return;
        }
        double totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        assignmentAverage = totalScore / scores.size();
    }

    public void generateRandomScore() {
        Random random = new Random();
        for (int i = 0; i < scores.size(); i++) {
            int randomNumber = random.nextInt(11); // Generates a random number between 0 and 10
            int score;
            if (randomNumber == 0) {
                score = random.nextInt(60);
            } else if (randomNumber <= 2) {
                score = random.nextInt(10, 60);
            } else if (randomNumber <= 4) {
                score = random.nextInt(10, 70);
            } else if (randomNumber <= 8) {
                score = random.nextInt(10, 80);
            } else {
                score = random.nextInt(11,90);
            }
            scores.set(i, score);
        }
        calcAssignmentAvg();
    }

    public static boolean isAssignmentsTotalWeightValid(Assignment... assignments) {
        double totalWeight = Arrays.stream(assignments).mapToDouble(Assignment::getWeight).sum();
        return Math.abs(totalWeight - 1.0) < 0.0001; // Using tolerance for double comparison
    }
}
