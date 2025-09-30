package me.pauep.crud2dam.dao;

import me.pauep.crud2dam.model.Degree;

import java.util.List;

public interface DegreeDAO {
    Degree createDegree(Degree teacher);
    Degree updateDegree(int id, Degree newDegree);
    Degree deleteDegree(int id);
    List<Degree> getAllDegrees();
    Degree getDegreeById(int id);
}
