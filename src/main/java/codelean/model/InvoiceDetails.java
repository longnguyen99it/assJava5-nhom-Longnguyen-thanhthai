package codelean.model;

import javax.persistence.*;

@Entity
@Table(name = "envoicedetails")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeStay_Id")
    private HomeStay homeStayId;

    @ManyToOne
    @JoinColumn(name = "invoice_Id")
    private Invoice invoiceId;

    @ManyToOne
    @JoinColumn(name = "account_Id")
    private Account account;
    //gen code

    public InvoiceDetails() {
    }
    public InvoiceDetails(HomeStay homeStay,Invoice invoice,Account account) {
        this.homeStayId = homeStay;
        this.invoiceId = invoice;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeStay getHomeStayId() {
        return homeStayId;
    }

    public void setHomeStayId(HomeStay homeStayId) {
        this.homeStayId = homeStayId;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
