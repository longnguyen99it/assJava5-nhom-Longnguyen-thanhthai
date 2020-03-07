package codelean.service;

import codelean.model.Account;
import codelean.model.Invoice;
import codelean.model.InvoiceDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface InvoiceDetailsService {
    List<InvoiceDetails> showInvoiceDetailsAdmin(String email,String name);
    List<InvoiceDetails> listInvoiceById(Long id);
    Iterable<InvoiceDetails> listInvoiceByAccount(Long id);
    Iterable<InvoiceDetails> listInvoiceByInvoice(Long id);
    Iterable<InvoiceDetails> finByAll();
    Optional<InvoiceDetails> finById(Long id);
    void save(InvoiceDetails invoiceDetails);
    void remove(Long id);
}
