package me.pauep.crud2dam.controller;

import me.pauep.crud2dam.dao.impl.DegreeDAOImpl;
import me.pauep.crud2dam.domain.model.Degree;
import me.pauep.crud2dam.view.DegreeView;

import java.util.List;

public class DegreeController {
    private final DegreeDAOImpl dao = new DegreeDAOImpl();
    private final DegreeView view = new DegreeView();

    public void run() {
        int option = 99;
        while (option != 0) {
            option = 99;
            option = view.menu();

            switch (option) {
                case 1 -> createDegree();
                case 2 -> updateDegreeById();
                case 3 -> deleteDegreeById();
                case 4 -> getDegreeById();
                case 5 -> getAllDegrees();
            }
        }
    }

    public void createDegree() {
        Degree degree = view.promptDegreeData();
        degree = dao.createDegree(degree);
        System.out.println("Created degree with id: " + degree.getId());
        view.printDegree(degree);    }

    public void updateDegreeById() {
        int degreeId = view.promptDegreeId();
        Degree degree = view.promptDegreeData();
        degree = dao.updateDegree(degreeId, degree);

        System.out.println("Updated degree with id: " + degree.getId());
        view.printDegree(degree);
    }

    public void deleteDegreeById() {
        int degreeId = view.promptDegreeId();
        Degree degree = dao.deleteDegreeById(degreeId);
        
        if (degree == null) {
            return;
        }

        System.out.println("Deleted degree with id: " + degree.getId());
        view.printDegree(degree);
    }

    public void getDegreeById() {
        int degreeId = view.promptDegreeId();
        Degree degree = dao.getDegreeById(degreeId);

        System.out.println("Degree with id: " + degree.getId());
        view.printDegree(degree);
    }

    public void getAllDegrees() {
        List<Degree> degrees = dao.getAllDegrees();

        view.printDegrees(degrees);
    }
}