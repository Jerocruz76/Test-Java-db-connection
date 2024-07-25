package org.example;
import java.util.Scanner;
import org.example.clases.DBConnection;
import org.example.clases.Student;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.connect();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("menu:\n 1.Add student \n 2. Delete student \n 3. Exit \n 4. Show students \n 5. Show student by id");
            String menu = scanner.nextLine();
            switch (menu){
                case "1":
                    System.out.println("Enter the name");
                    String name = scanner.nextLine();
                    System.out.println("Enter the lastname");
                    String lastname = scanner.nextLine();
                    System.out.println("Enter the age");
                    int age = Integer.parseInt(scanner.nextLine());
                    Student student = new Student(name, lastname, age);
                    dbConnection.addStudent(student);
                    break;
                case "2":
                    System.out.println("Enter the id you want to delete");
                    int id = Integer.parseInt(scanner.nextLine());
                    dbConnection.deleteStudent(id);
                    break;
                case "3":
                    System.out.println("Leaving...");
                    scanner.close();
                    dbConnection.disconnect();
                    flag = false;
                    break;
                case "4":
                    dbConnection.ShowStudents();
                    break;
                case "5":
                    System.out.println("Enter the id you want to see");
                    int idToSearch = Integer.parseInt(scanner.nextLine());
                    dbConnection.ShowByID(idToSearch);
                    break;
                default:
                    System.out.println("Please, enter a valid option");
            }
        }
    }
}