package me.pauep.crud2dam.view;

import me.pauep.crud2dam.Main;
import me.pauep.crud2dam.domain.model.Degree;

import java.util.List;
import java.util.Scanner;

public class DegreeView {
    public int menu() {
        int option;
        printMenu();
        option = Main.readInt(0, 5);
        return option;
    }

    public Degree promptDegreeData() {
        String name;
        int teacherId;

        try {
            System.out.println("Enter the degree name: ");
            name = Main.SC.nextLine().trim();

            System.out.println("Enter the degree teacher id: ");
            teacherId = Main.readInt();
        } catch (Exception _) {
            System.out.println("[ERROR] Unexpected issue");
            return null;
        }

        return new Degree(name, teacherId);
    }

    public int promptDegreeId() {
        return Main.readInt();
    }

    public void printDegrees(List<Degree> degrees) {
        if (degrees.isEmpty()) System.out.println("No degrees.");

        for (Degree degree : degrees) {
            this.printDegree(degree);
        }
    }

    public void printDegree(Degree degree) {
        System.out.println(
                "Id: " + degree.getId() +
                        "\n Name: " + degree.getName() +
                        "\n Teacher id: " + degree.getTeacherId()
        );
    }

    private static void printMenu() {
        System.out.println("=== DEGREE MANAGER ===");
        System.out.println("1. Add Degree");
        System.out.println("2. Update Degree");
        System.out.println("3. Delete Degree");
        System.out.println("4. List Degree By Id");
        System.out.println("5. List All Degrees");
        System.out.println("0. Exit");
    }
}
