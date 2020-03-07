package codelean.service;

import codelean.model.Account;
import codelean.model.Invoice;
import codelean.model.InvoiceDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface InvoiceService {
    List<Invoice> showInvoiceAdmin();
    Iterable<Invoice> finByAll();
    Optional<Invoice> finById(Long id);
    void save(Invoice invoice);
    void remove(Long id);
}
