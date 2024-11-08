import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfStudents;

        while (true) {
            try {
                // Ask user how many students they want to enter grades for
                System.out.println("Enter the number of students you want to record grades for: ");
                numberOfStudents = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character because of scanning nextInt
                System.out.println();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer for number of students you want to record grades for.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Initialize arrays to store student names, subjects count, and grades
        String[] studentNames = new String[numberOfStudents];
        int[] subjectCount = new int[numberOfStudents];
        double[][] studentGrades = new double[numberOfStudents][];

        // For each student, gather name, number of subjects, and grades
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Student " + (i + 1));

            System.out.print("Enter the name of the student " + (i + 1) + ": ");
            studentNames[i] = scanner.nextLine();


            while (true) {
                try {
                    System.out.print("Enter the number of subjects for the " + studentNames[i] + ": ");
                    subjectCount[i] = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character because of scanning nextInt
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer for number of subjects you want to record grades for.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            // Initialize grades for each subject
            studentGrades[i] = new double[subjectCount[i]];

            // Collect grades for each subject
            for (int j = 0; j < subjectCount[i]; j++) {
                while (true) {
                    try {
                        System.out.print("Enter grade for subject " + (j + 1) + ": ");
                        studentGrades[i][j] = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character because of scanning nextDouble
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid grade.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                }
            }
            System.out.println();
        }

        // Calculate and display the average grade for each student
        System.out.println("Student Averages:");
        System.out.println("-----------------");
        double[] averageGrades = new double[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            double sum = 0;
            for (int j = 0; j < subjectCount[i]; j++) {
                sum += studentGrades[i][j];
            }
            averageGrades[i] = sum / subjectCount[i];
            System.out.printf("%s %.2f%n", studentNames[i], averageGrades[i]);
        }

        // Find and display the student with the highest average grade
        int highestIndex = 0;
        for (int i = 0; i < numberOfStudents; i++) {
            if (averageGrades[i] > averageGrades[highestIndex]) {
                highestIndex = i;
            }
        }

        System.out.println();
        System.out.printf("Student with the highest average grade is %s with average grade %.2f%n", studentNames[highestIndex], averageGrades[highestIndex]);
        System.out.println();

        while (true) {
            // Display all grades for any student by name
            System.out.print("Enter the name of the student to view grades or type 'q' to quit: ");
            String studentName = scanner.nextLine();
            boolean found = false;

            if (studentName.equalsIgnoreCase("q")) {
                System.out.println();
                System.out.println("Exiting...");
                break;
            }

            for (int i = 0; i < numberOfStudents; i++) {
                if (studentNames[i].equalsIgnoreCase(studentName)) {
                    found = true;
                    System.out.println();
                    System.out.println("Grades for " + studentNames[i] + ":");
                    for (int j = 0; j < subjectCount[i]; j++) {
                        System.out.println("Subject " + (j + 1) + ": " + studentGrades[i][j]);
                    }
                    System.out.println();
                    break;
                }
            }

            if (!found) {
                System.out.println();
                System.out.println("Student " + studentName + " not found");
                System.out.println();
            }
        }
    }
}