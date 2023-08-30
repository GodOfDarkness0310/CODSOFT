import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[] marks = new int[numSubjects];
        int[] maxScores = new int[numSubjects];
        
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter maximum score for subject " + (i + 1) + ": ");
            maxScores[i] = scanner.nextInt();
        }

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        int totalMarks = 0;
        int totalMaxScores = 0;

        for (int i = 0; i < numSubjects; i++) {
            totalMarks += marks[i];
            totalMaxScores += maxScores[i];
        }

        double averagePercentage = (double) totalMarks / totalMaxScores * 100;
        String grade = calculateGrade(averagePercentage);
        String message = getMessage(grade);

        System.out.println("\nResults:");
        System.out.println("=============================================");
        System.out.printf("| %-20s | %-10s | %-10s | %-12s |\n", "Subject", "Marks", "Max Score", "Percentage");
        System.out.println("=============================================");

        for (int i = 0; i < numSubjects; i++) {
            double subjectPercentage = (double) marks[i] / maxScores[i] * 100;
            System.out.printf("| %-20s | %-10d | %-10d | %-10.2f%% |\n", 
                    "Subject " + (i + 1), marks[i], maxScores[i], subjectPercentage);
        }

        System.out.println("=============================================");
        System.out.printf("| %-20s | %-10d | %-10d | %-10.2f%% |\n", "Total", totalMarks, totalMaxScores, averagePercentage);
        System.out.printf("| %-20s | %-10s |\n", "Grade", grade);
        System.out.println("=============================================");
        System.out.println("\n" + message);

        scanner.close();
    }

    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static String getMessage(String grade) {
        switch (grade) {
            case "A":
                return "Congratulations! You've achieved an excellent grade!";
            case "B":
                return "Well done! You've earned a good grade.";
            case "C":
                return "You've received a satisfactory grade.";
            case "D":
                return "You've passed, but there's room for improvement.";
            default:
                return "Keep working hard for better results next time.";
        }
    }
}
