package ru.job4j.solid.ocp;

import java.util.ArrayList;

public class ReportGenerator {
    private final DataFromConsole data;

    public ReportGenerator(DataFromConsole data) {
        this.data = data;
    }

    public String generate(String format) {
        String rsl = null;
        if ("PDF".equals(format)) {
            rsl = "PDF Report: " + data.text;
        }
        return rsl;
    }
}





