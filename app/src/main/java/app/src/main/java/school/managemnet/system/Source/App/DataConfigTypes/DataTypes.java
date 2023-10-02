package app.src.main.java.school.managemnet.system.Source.App.DataConfigTypes;

import java.util.Scanner;

import app.src.main.java.school.managemnet.system.Source.App.Database.QueryAPI;

public class DataTypes {
    QueryAPI DQuery = null;
    Scanner scanner = null;
    int integer = -1000; // Random Value

    public DataTypes(Scanner sc, QueryAPI query, int x)
    {
        scanner = sc;
        DQuery = query;
        integer = x;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public QueryAPI getDQuery() {
        return DQuery;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public void setDQuery(QueryAPI dQuery) {
        DQuery = dQuery;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
