import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double score;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }
}

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("Welcome to the Student Grade Manager!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a student");
            System.out.println("2. Display summary report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine().trim();
                
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    continue;
                }
                
                double score = -1;
                while (score < 0 || score > 100) {
                    System.out.print("Enter student score (0-100): ");
                    try {
                        score = Double.parseDouble(scanner.nextLine().trim());
                        if (score < 0 || score > 100) {
                            System.out.println("Score must be between 0 and 100.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
                
                students.add(new Student(name, score));
                System.out.println("Student added successfully.");
                
            } else if (choice == 2) {
                if (students.isEmpty()) {
                    System.out.println("No students added yet.");
                } else {
                    displaySummary(students);
                }
            } else if (choice == 3) {
                System.out.println("Exiting program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
        
        scanner.close();
    }

    private static void displaySummary(ArrayList<Student> students) {
        System.out.println("\n--- Summary Report ---");
        System.out.println(String.format("%-20s | %s", "Student Name", "Score"));
        System.out.println("--------------------------------");
        
        double totalScore = 0;
        double highestScore = Double.MIN_VALUE;
        double lowestScore = Double.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        for (Student student : students) {
            System.out.println(String.format("%-20s | %.2f", student.name, student.score));
            totalScore += student.score;
            
            if (student.score > highestScore) {
                highestScore = student.score;
                highestStudent = student.name;
            }
            if (student.score < lowestScore) {
                lowestScore = student.score;
                lowestStudent = student.name;
            }
        }

        double averageScore = totalScore / students.size();

        System.out.println("--------------------------------");
        System.out.println(String.format("Total Students: %d", students.size()));
        System.out.println(String.format("Average Score: %.2f", averageScore));
        System.out.println(String.format("Highest Score: %.2f (%s)", highestScore, highestStudent));
        System.out.println(String.format("Lowest Score:  %.2f (%s)", lowestScore, lowestStudent));
        System.out.println("--------------------------------");
    }
}
