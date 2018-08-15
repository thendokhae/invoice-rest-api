package za.co.digitalplatoon.controller.rest.controllers;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.controller.rest.entities.Invoice;
import za.co.digitalplatoon.controller.rest.repositories.InMemoryRepository;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Invoice.class)
@RequestMapping(value = "/invoices", produces = "application/json")
public class InvoiceController {

    @Autowired
    private InMemoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> findAllOrders() {
        List<Invoice> invoices = repository.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Invoice> createOrder(@RequestBody Invoice invoice) {
        Invoice createdInvoice = repository.create(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> findOrderById(@PathVariable Long id) {
        Invoice invoice = repository.findById(id);

        if (invoice != null) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
