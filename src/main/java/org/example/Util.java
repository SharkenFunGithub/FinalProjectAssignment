package org.example;

import java.util.Scanner;

public class Util {

    // Method to convert a string to title case
    public static String toTitleCase(String strIn) {
        if (strIn == null || strIn.isEmpty()) { // Check if the input is empty
            return strIn; // If it is, return it as is
        }

        String[] words = strIn.split(" "); // Split the string into words at each space

        for (int i = 0; i < words.length; i++) { // Loop through each word
            if (!words[i].isEmpty()) { // Check if the word is not empty
                char firstLetter = Character.toUpperCase(words[i].charAt(0)); // Get the first letter and capitalize it
                String restOfWord = words[i].substring(1).toLowerCase(); // Get the rest of the word and make it lowercase
                words[i] = firstLetter + restOfWord; // Combine the first letter and the rest of the word
            }
        }

        return String.join(" ", words); // Join the words back into a single string
    }

    // Main method to demonstrate the toTitleCase function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to convert to title case: ");
        String input = scanner.nextLine();
        String titleCaseString = toTitleCase(input);
        System.out.println("Title Case Output: " + titleCaseString);
        scanner.close();
    }
}
