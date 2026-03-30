import java.io.*;
import java.util.*;

class Student implements Serializable {
    int roll;
    String name;
    double marks;

    Student(int roll, String name, double marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }
}

public class Main {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // 🔹 Load data from file
    static void loadFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            students = (ArrayList<Student>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    // 🔹 Save data to file
    static void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
            oos.writeObject(students);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving data!");
        }
    }

    // 🔹 Add Student
    static void addStudent() {
        System.out.print("Enter Roll: ");
        int roll = sc.nextInt();
        sc.nextLine();

        // Check duplicate
        for (Student s : students) {
            if (s.roll == roll) {
                System.out.println("Student already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        // Validation
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks (0-100 only)!");
            return;
        }

        students.add(new Student(roll, name, marks));
        System.out.println("Student Added!");
    }

    // 🔹 Display Students
    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println("Roll: " + s.roll + ", Name: " + s.name + ", Marks: " + s.marks);
        }
    }

    // 🔹 Search Student
    static void searchStudent() {
        System.out.print("Enter Roll: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.roll == roll) {
                System.out.println("Found -> Name: " + s.name + ", Marks: " + s.marks);
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    // 🔹 Update Student
    static void updateStudent() {
        System.out.print("Enter Roll: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.roll == roll) {
                sc.nextLine();

                System.out.print("Enter New Name: ");
                s.name = sc.nextLine();

                System.out.print("Enter New Marks: ");
                double marks = sc.nextDouble();

                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks!");
                    return;
                }

                s.marks = marks;
                System.out.println("Student Updated!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    // 🔹 Delete Student
    static void deleteStudent() {
        System.out.print("Enter Roll: ");
        int roll = sc.nextInt();

        Iterator<Student> it = students.iterator();

        while (it.hasNext()) {
            Student s = it.next();
            if (s.roll == roll) {
                it.remove();
                System.out.println("Student Deleted!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    // 🔹 Main Menu
    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}