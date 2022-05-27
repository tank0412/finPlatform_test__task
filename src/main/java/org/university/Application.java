package org.university;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input();
        DB db = new DB();

        System.out.println("Students APP v0.1");
        System.out.println("Running Flyway migration....");
        db.migrateFlyway();
        System.out.println("#########################################################################################");

        String userChoice = null;
        while (!"0".equals(userChoice)) {
            System.out.println("Press 0 for exit");
            System.out.println("Press 1 to Select all student");
            System.out.println("Press 2 to delete student");
            System.out.println("Press 3 to add student");
            userChoice = scanner.next();
            switch (userChoice) {
                case "1": {
                    input.processSelectChoice(scanner);
                    break;
                }
                case "2": {
                    input.processDeleteChoice(scanner);
                    break;
                }
                case "3": {
                    input.processAddNewStudentChoice(scanner);
                    break;
                }
            }
        }
    }
}
