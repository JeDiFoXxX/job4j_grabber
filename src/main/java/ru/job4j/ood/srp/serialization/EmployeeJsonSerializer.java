package ru.job4j.ood.srp.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Type;
import java.util.Calendar;

public class EmployeeJsonSerializer implements JsonSerializer<Employee> {
    private final DateTimeParser<Calendar> dateTimeParser;

    public EmployeeJsonSerializer(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public JsonElement serialize(Employee e, Type t, JsonSerializationContext jsc) {
        JsonObject json = new JsonObject();
        json.addProperty("name", e.getName());
        json.addProperty("hired", dateTimeParser.parse(e.getHired()));
        json.addProperty("fired", dateTimeParser.parse(e.getFired()));
        json.addProperty("salary", e.getSalary());
        return json;
    }
}
