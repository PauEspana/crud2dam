package me.pauep.crud2dam.controller;

import me.pauep.crud2dam.dao.impl.TeacherDAOImpl;
import me.pauep.crud2dam.domain.model.Teacher;
import me.pauep.crud2dam.view.TeacherView;

import java.util.List;

public class TeacherController {
    private final TeacherDAOImpl dao = new TeacherDAOImpl();
    private final TeacherView view = new TeacherView();

    public void run() {
        int option = 99;
        while (option != 0) {
            option = view.menu();
            
            switch (option) {
                case 1 -> createTeacher();
                case 2 -> updateTeacherById();
                case 3 -> deleteTeacherById();
                case 4 -> getTeacherById();
                case 5 -> getAllTeachers();
            }
        }
    }
    
    public void createTeacher() {
        Teacher teacher = view.promptTeacherData();
        teacher = dao.createTeacher(teacher);
        System.out.println("Created teacher with id: " + teacher.getId());
        view.printTeacher(teacher);    }

    public void updateTeacherById() {
        int teacherId = view.promptTeacherId();
        Teacher teacher = view.promptTeacherData();
        teacher = dao.updateTeacher(teacherId, teacher);

        System.out.println("Updated teacher with id: " + teacher.getId());
        view.printTeacher(teacher);
    }

    public void deleteTeacherById() {
        int teacherId = view.promptTeacherId();
        Teacher teacher = dao.deleteTeacherById(teacherId);

        if (teacher == null) {
            return;
        }
        
        System.out.println("Deleted teacher with id: " + teacher.getId());
        view.printTeacher(teacher);
    }

    public void getTeacherById() {
        int teacherId = view.promptTeacherId();
        Teacher teacher = dao.getTeacherById(teacherId);

        System.out.println("Teacher with id: " + teacher.getId());
        view.printTeacher(teacher);    
    }

    public void getAllTeachers() {
        List<Teacher> teachers = dao.getAllTeachers();

        view.printTeachers(teachers);
    }
}