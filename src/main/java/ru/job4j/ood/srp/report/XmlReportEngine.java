package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import ru.job4j.ood.srp.wrapper.EmployeeListWrapper;

import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private final Marshaller marshaller;

    public XmlReportEngine(Store store) throws JAXBException {
        this.store = store;
        this.marshaller = JAXBContext
                .newInstance(Employee.class, EmployeeListWrapper.class)
                .createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringWriter writer = new StringWriter();
        try {
            EmployeeListWrapper wrapper = new EmployeeListWrapper(store.findBy(filter));
            marshaller.marshal(wrapper, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
