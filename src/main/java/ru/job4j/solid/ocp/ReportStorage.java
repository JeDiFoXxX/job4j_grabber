package ru.job4j.solid.ocp;

import java.util.ArrayList;

public class ReportStorage {
    private final ArrayList<String> list = new ArrayList<>();

    public void add(String report) {
        list.add(report);
    }

    public ArrayList<String> getAll() {
        return list;
    }
}