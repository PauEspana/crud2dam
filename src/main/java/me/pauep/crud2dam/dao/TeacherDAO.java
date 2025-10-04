package me.pauep.crud2dam.dao;

import me.pauep.crud2dam.domain.model.Teacher;

import java.util.List;

public interface TeacherDAO {
    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(int id, Teacher newTeacher);
    Teacher deleteTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(int id);
}
