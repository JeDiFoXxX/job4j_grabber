package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.sort.SortedDescSalaryByEmployee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;

class HRReportTest {
    @Test
    public void whenGenerateReportThenSortedBySalaryDesc() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Comparator<Employee> comparator = new SortedDescSalaryByEmployee();
        Report hr = new HRReport(store, comparator);
        String separator = System.lineSeparator();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 150));
        store.add(new Employee("Maria", now, now, 120));
        String expected = new StringBuilder()
                .append("Name").append(" | ").append("Salary").append(separator)
                .append("Petr").append(" | ").append(150.0).append(separator)
                .append("Maria").append(" | ").append(120.0).append(separator)
                .append("Ivan").append(" | ").append(100.0).append(separator)
                .toString();
        assertThat(expected).isEqualToIgnoringCase(hr.generate(employee -> true));
    }
}