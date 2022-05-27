package org.university;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Input {
    private final DB db = new DB();

    public void processSelectChoice(Scanner scanner) {
        try {
            db.selectAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Some error occured. Try again.");
        }
    }

    public void processDeleteChoice(Scanner scanner) {
        System.out.println("Print student id");
        String studentId = scanner.next();
        int id = 0;
        try {
            id = Integer.parseInt(studentId);
        } catch (Exception e) {
            System.out.println("Incorrect input");
            return;
        }
        db.deleteStudentById(id);
    }

    public void processAddNewStudentChoice(Scanner scanner) {
        System.out.println("Print student name");
        String name = scanner.next();
        System.out.println("Print student surname");
        String surname = scanner.next();
        System.out.println("Print student middleName");
        String middleName = scanner.next();

        System.out.println("Print student birth date in a format: 2017-01-13");
        String studentBirthDate = scanner.next();
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(studentBirthDate);
        }
        catch (Exception e) {
            System.out.println("Incorrect input! Returning....");
            return;
        }

        System.out.println("Print student group number");
        String studentGroupNumber = scanner.next();

        db.createNewStudent(name, surname, middleName, localDate, studentGroupNumber);

    }
}
