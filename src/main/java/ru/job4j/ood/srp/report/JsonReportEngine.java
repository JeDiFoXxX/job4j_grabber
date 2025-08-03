package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.serialization.EmployeeJsonSerializer;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class JsonReportEngine implements Report {
    private final Store store;
    private final Gson gson;

    public JsonReportEngine(Store store) {
        this.store = store;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Employee.class, new EmployeeJsonSerializer(new ReportDateTimeParser()))
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
