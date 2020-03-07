package codelean.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private int totalPrice;
    private int numberDate;
    private  int numberRoom;
    private String checkInDate;
    private String checkOutDate;

    @OneToMany
    private transient List<InvoiceDetails> invoiceDetails;
    //gen code

    public Invoice() {
    }
    public Invoice(String name, String email, int totalPrice, int numberDate, int numberRoom, String checkInDate, String checkOutDate) {
        this.name=name;
        this.email=email;
        this.totalPrice=totalPrice;
        this.numberDate=numberDate;
        this.numberRoom=numberRoom;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberDate() {
        return numberDate;
    }

    public void setNumberDate(int numberDate) {
        this.numberDate = numberDate;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
