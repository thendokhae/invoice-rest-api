package za.co.digitalplatoon.controller.rest.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.digitalplatoon.controller.rest.entities.Invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryRepository {
    @Autowired
    private IdGenerator idGenerator;

    private List<Invoice> elements = Collections.synchronizedList(new ArrayList<Invoice>());

    public Invoice create(Invoice element) {
        elements.add(element);
        return element;
    }

    public boolean delete(Long id) {
        for (Invoice invoice:elements) {
            if(invoice.getId() == id){
                elements.remove(invoice);
                return true;
            }
        }
        return false;
    }

    public List<Invoice> findAll() {
        return elements;
    }

    public Invoice findById(Long id) {
        for (Invoice invoice:elements) {
            if(invoice.getId() == id){
                return invoice;
            }
        }
        return null;
    }

    public int getCount() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    public boolean update(Long id, Invoice updated) {
        Invoice present = null;
        if (updated == null) {
            return false;
        }
        else {
            for (Invoice invoice:elements) {
                if(invoice.getId() == id){
                    elements.set(elements.indexOf(invoice), updated);
                    present = invoice;
                }
            }
            return updated.getId()==present.getId();
        }
    }

    public void updateIfExists(Invoice original, Invoice updated) {
        original.setClient(updated.getClient());
        original.setInvoiceDate(updated.getInvoiceDate());
        original.setLineItems(updated.getLineItems());
        original.setVatRate(updated.getVatRate());
    }
}
