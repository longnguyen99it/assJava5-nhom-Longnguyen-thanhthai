package codelean.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "homeStay")
public class HomeStay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String homeStayName;
    private String homeStayStyle;
    private int adults;
    private int child;
    private int bedRoom;
    private int bathRoom;
    private String convenient;
    private String size;
    private String address;
    private String image;
    private int price;
    private String status;
    @OneToMany
    private List<InvoiceDetails> invoiceDetailsLis;
    //gen code

    public HomeStay() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeStayName() {
        return homeStayName;
    }

    public void setHomeStayName(String homeStayName) {
        this.homeStayName = homeStayName;
    }

    public String getHomeStayStyle() {
        return homeStayStyle;
    }

    public void setHomeStayStyle(String homeStayStyle) {
        this.homeStayStyle = homeStayStyle;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(int bedRoom) {
        this.bedRoom = bedRoom;
    }

    public int getBathRoom() {
        return bathRoom;
    }

    public void setBathRoom(int bathRoom) {
        this.bathRoom = bathRoom;
    }

    public String getConvenient() {
        return convenient;
    }

    public void setConvenient(String convenient) {
        this.convenient = convenient;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InvoiceDetails> getInvoiceDetailsLis() {
        return invoiceDetailsLis;
    }

    public void setInvoiceDetailsLis(List<InvoiceDetails> invoiceDetailsLis) {
        this.invoiceDetailsLis = invoiceDetailsLis;
    }
}
