package codelean.service;

import codelean.model.HomeStay;
import codelean.model.Invoice;
import codelean.model.InvoiceDetails;
import codelean.repository.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<InvoiceDetails> showInvoiceDetailsAdmin(String email, String name) {
        String query ="select invdt from InvoiceDetails invdt where invdt.invoiceId.email = '"+email+"' and invdt.invoiceId.name = '"+name+"'";
        TypedQuery<InvoiceDetails> invoiceDetailsTypedQuery = entityManager.createQuery(query,InvoiceDetails.class);
        return invoiceDetailsTypedQuery.getResultList();
    }

    @Override
    public List<InvoiceDetails> listInvoiceById(Long id) {
        String query = "select inv from InvoiceDetails inv where inv.id = '"+id+"'";
        TypedQuery<InvoiceDetails> invoiceDetailsTypedQuery = entityManager.createQuery(query,InvoiceDetails.class);
        return invoiceDetailsTypedQuery.getResultList();
    }

    @Override
    public Iterable<InvoiceDetails> listInvoiceByAccount(Long id) {
        String query = "select inv from InvoiceDetails inv where inv.account.id = '"+id+"'";
        TypedQuery<InvoiceDetails> invoiceDetailsTypedQuery = entityManager.createQuery(query,InvoiceDetails.class);

        return invoiceDetailsTypedQuery.getResultList();
    }

    @Override
    public Iterable<InvoiceDetails> listInvoiceByInvoice(Long id) {
        String query = "select inv from InvoiceDetails inv where inv.invoiceId.id = '"+id+"'";
        TypedQuery<InvoiceDetails> invoiceDetailsTypedQuery = entityManager.createQuery(query,InvoiceDetails.class);
        return invoiceDetailsTypedQuery.getResultList();
    }

    @Override
    public Iterable<InvoiceDetails> finByAll() {
        return invoiceDetailsRepository.findAll();
    }

    @Override
    public Optional<InvoiceDetails> finById(Long id) {
        return invoiceDetailsRepository.findById(id);
    }

    @Override
    public void save(InvoiceDetails invoiceDetails) {
        invoiceDetailsRepository.save(invoiceDetails);
    }

    @Override
    public void remove(Long id) {
        invoiceDetailsRepository.deleteById(id);
    }
}
