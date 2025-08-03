package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ITReportTest {
    @Test
    void whenGenerateITReportThenCorrectCSV() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        ITReport report = new ITReport(store, parser);
        String separator = System.lineSeparator();
        String expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary").append(separator)
                .append("Ivan,")
                .append(parser.parse(now)).append(",")
                .append(parser.parse(now)).append(",")
                .append(100.0)
                .append(separator)
                .toString();
        assertThat(expected).isEqualToIgnoringCase(report.generate(emp -> true));
    }
}