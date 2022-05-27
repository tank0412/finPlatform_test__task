package org.university;

import org.flywaydb.core.Flyway;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("Students APP v0.1");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Running Flyway migration....");
        Flyway flyway = Flyway.configure()
                .dataSource(DB.DB_CONNECTION, DB.DB_USER, DB.DB_PASSWORD)
                .locations("db/migration")
                .load();
        flyway.migrate();
        System.out.println("#########################################################################################");

        String userChoice = null;
        while(!"0".equals(userChoice)) {
            System.out.println("Press 0 for exit");
            System.out.println("Press 1 to Select all student");
            userChoice = scanner.next();
            switch (userChoice) {
                case "1": {
                    DB.selectAllStudents();
                    break;
                }
            }
        }
    }
}
