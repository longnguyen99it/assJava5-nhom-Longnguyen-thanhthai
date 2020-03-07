package codelean.repository;

import codelean.model.Account;
import codelean.model.HomeStay;
import codelean.model.Invoice;
import codelean.model.InvoiceDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository extends PagingAndSortingRepository<InvoiceDetails,Long> {
    Iterable<InvoiceDetails> findAllByAccount(Account account);
    Iterable<InvoiceDetails> findAllByInvoiceId(Invoice invoice);
}
