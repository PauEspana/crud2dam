package me.pauep.crud2dam;

import me.pauep.crud2dam.controller.DegreeController;
import me.pauep.crud2dam.controller.TeacherController;

import java.util.Scanner;

public class Main {
    public static final Scanner SC = new Scanner(System.in);
    
    public static void main(String[] args) {
        int option = 99;
        
        while (option != 0) {
            printInitialMenu();
            option = readInt(0, 2);
            
            manageOption(option);
        }
    }

    public static void manageOption(int option) {
        DegreeController degreeController = new DegreeController();
        TeacherController teacherController = new TeacherController();;
        
        switch (option) {
            case 1 -> teacherController.run();
            case 2-> degreeController.run();
        }
    }

    public static int readInt(int min, int max) {
        System.out.print("Enter a number: ");
        while (true) {
            try { 
                int sel = Integer.parseInt(SC.nextLine().trim());
                if (sel >= min && sel <= max) return sel;
                else throw new NumberFormatException();
            } catch (NumberFormatException _) { System.out.print("[INVALID OPTION] Enter a number: "); }
        }
    }    
    
    public static int readInt() {
        System.out.print("Enter a number: ");
        while (true) {
            try {
                return Integer.parseInt(SC.nextLine().trim());
            } catch (NumberFormatException _) { System.out.print("[INVALID OPTION] Enter a number: "); }
        }
    }
    
    public static void printInitialMenu() {
        System.out.println("=== 2 DAM DB MANAGER ===");
        System.out.println("1. Manage Teachers");
        System.out.println("2. Manage Degrees");
        System.out.println("0. Exit");
    }
}