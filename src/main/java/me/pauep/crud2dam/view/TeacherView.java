package me.pauep.crud2dam.view;

import me.pauep.crud2dam.Main;
import me.pauep.crud2dam.domain.model.Teacher;

import java.util.List;
import java.util.Scanner;

public class TeacherView {
    public int menu() {
        int option;
        printMenu();
        option = Main.readInt(0, 5);
        return option;
    }

    public Teacher promptTeacherData() {
        String name; 
        String surname;
        
        try {
            System.out.println("Enter the teacher name: ");
            name = Main.SC.nextLine().trim();
            
            System.out.println("Enter the teacher surname: ");
            surname = Main.SC.nextLine().trim();
        } catch (Exception _) {
            System.out.println("[ERROR] Unexpected issue");
            return null;
        }

        return new Teacher(name, surname);
    }   
    
    public int promptTeacherId() {
        return Main.readInt();
    }
    
    public void printTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) System.out.println("No teachers.");
        
        for (Teacher teacher : teachers) {
            this.printTeacher(teacher);
        }
    }
    
    public void printTeacher(Teacher teacher) {
        System.out.println(
                "Id: " + teacher.getId() + 
                "\n Name: " + teacher.getName() +
                "\n Surname: " + teacher.getSurname()
        );
    }
    
    private static void printMenu() {
        System.out.println("=== TEACHER MANAGER ===");
        System.out.println("1. Add Teacher");
        System.out.println("2. Update Teacher");
        System.out.println("3. Delete Teacher");
        System.out.println("4. List Teacher By Id");
        System.out.println("5. List All Teachers");
        System.out.println("0. Exit");
    }
}
