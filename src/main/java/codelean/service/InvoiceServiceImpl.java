package codelean.service;

import codelean.model.Invoice;
import codelean.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Invoice> showInvoiceAdmin() {
        String query = "select inv from Invoice inv group by inv.email,inv.name";
        TypedQuery<Invoice> invoiceTypedQuery = entityManager.createQuery(query,Invoice.class);
        return invoiceTypedQuery.getResultList();
    }

    @Override
    public Iterable<Invoice> finByAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> finById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void remove(Long id) {
        invoiceRepository.deleteById(id);
    }
}
