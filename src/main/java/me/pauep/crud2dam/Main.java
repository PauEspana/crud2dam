package me.pauep.crud2dam;

import me.pauep.crud2dam.util.DatabaseManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseManager.getDatabaseConnection();
    }
}