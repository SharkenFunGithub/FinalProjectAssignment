package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1; // Default nextId starts from 1

    public Department(String departmentName) {
        if (validateDepartmentName(departmentName)) {
            this.departmentName = departmentName;
            this.departmentId = generateDepartmentId();
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    private static String generateDepartmentId() {
        // Generate departmentId starting with 'D' followed by the nextId, then increment nextId
        return "D" + String.format("%02d", nextId++);
    }

    public static boolean validateDepartmentName(String departmentName) {
        // Check if department name is null or empty
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        // Check if department name contains only letters or space
        for (char c : departmentName.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }
}
