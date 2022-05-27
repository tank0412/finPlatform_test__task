package org.university;

import org.flywaydb.core.Flyway;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Students APP v0.1");
        System.out.println("Running Flyway migration....");
        migrateFlyway();
        System.out.println("#########################################################################################");

        String userChoice = null;
        while (!"0".equals(userChoice)) {
            System.out.println("Press 0 for exit");
            System.out.println("Press 1 to Select all student");
            System.out.println("Press 2 to delete student");
            userChoice = scanner.next();
            switch (userChoice) {
                case "1": {
                    DB.selectAllStudents();
                    break;
                }
                case "2": {
                    processDeleteChoice(scanner);
                }
            }
        }
    }

    private static void migrateFlyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(DB.DB_CONNECTION, DB.DB_USER, DB.DB_PASSWORD)
                .locations("db/migration")
                .load();
        flyway.migrate();
    }

    private static void processDeleteChoice(Scanner scanner) {
        System.out.println("Print student id");
        String studentId = scanner.next();
        int id = 0;
        try {
            id = Integer.parseInt(studentId);
        } catch (Exception e) {
            System.out.println("Incorrect input");
            return;
        }
        DB.deleteStudentById(id);
    }
}
