package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class AccountingReportTest {
    @Test
    public void whenGenerateAccountingReportThenCorrectCurrencyConversion() {
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        String separator = System.lineSeparator();
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report accountingReport = new AccountingReport(
                store,
                parser,
                converter,
                Currency.RUB,
                Currency.USD
        );
        store.add(worker);
        String expected = new StringBuilder()
                .append("Name")
                .append(" | ")
                .append("Hired")
                .append(" | ")
                .append("Fired")
                .append(" | ")
                .append("Salary ").append(Currency.RUB)
                .append(" | ")
                .append("Salary ").append(Currency.USD)
                .append(separator)
                .append("Ivan")
                .append(" | ")
                .append(parser.parse(now))
                .append(" | ")
                .append(parser.parse(now))
                .append(" | ")
                .append(100.0)
                .append(" | ")
                .append(converter.convert(Currency.RUB, 100.0, Currency.USD))
                .append(separator)
                .toString();
        assertThat(expected).isEqualToIgnoringCase(accountingReport.generate(employee -> true));
    }
}