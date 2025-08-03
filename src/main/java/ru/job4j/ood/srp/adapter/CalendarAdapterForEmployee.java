package ru.job4j.ood.srp.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;

import java.util.Calendar;

public class CalendarAdapterForEmployee extends XmlAdapter<String, Calendar> {
    @Override
    public String marshal(Calendar calendar) throws Exception {
        return new ReportDateTimeParser().parse(calendar);
    }

    @Override
    public Calendar unmarshal(String s) throws Exception {
        return null;
    }
}
