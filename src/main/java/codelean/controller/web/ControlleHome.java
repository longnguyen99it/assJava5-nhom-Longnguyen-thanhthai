package codelean.controller.web;

import codelean.model.Account;
import codelean.model.HomeStay;
import codelean.model.Invoice;
import codelean.model.InvoiceDetails;
import codelean.modelJava.dateTime;
import codelean.repository.InvoiceDetailsRepository;
import codelean.service.AccountService;
import codelean.service.HomeStayService;
import codelean.service.InvoiceDetailsService;
import codelean.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(name = "PolyProHomeStay/")
public class ControlleHome {
    public static Long accountId = (long)1;
    @Autowired
    private HomeStayService homeStayService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;
    @GetMapping("/")
    public ModelAndView home() {
        List<HomeStay> list = homeStayService.findAllPageById(0,6,"id");
        ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/homePage");
        modelAndView.addObject("listHomeStayByIdDesc", list);
        if(accountId==1){
            modelAndView.addObject("account",null);
        }else{
            modelAndView.addObject("account",accountService.finById(accountId).get());
        }
        return modelAndView;
    }

    @GetMapping("showHomeStayById/{id}")
    public ModelAndView showhomeStay(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/showHomeStay");
        Optional<HomeStay> homeStayOptional = homeStayService.findById(id);
        Iterable<HomeStay> homeStayIterable = homeStayService.findAllByPage(0,6);
        modelAndView.addObject("homeStayByID",homeStayOptional.get());
        modelAndView.addObject("listLienQuan",homeStayIterable);
        return modelAndView;
    }
    @GetMapping("seach")
    public ModelAndView seachHomeStay(@RequestParam("address")String address){
        ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/seach");
        modelAndView.addObject("listByAddres",homeStayService.finByAddress(address));
        modelAndView.addObject("addressVl",address);
        return modelAndView;
    }

    @GetMapping("/booking")
    public ModelAndView loi(@RequestParam("id")Long id, @RequestParam("numberRoom")int numberRoom,
                            @RequestParam("checkOut") Date checkOut, @RequestParam("checkIn") Date checkIn){
        int numberDate = (int)dateTime.calculateTheNumber(checkIn,checkOut);
        if(numberDate < 0){
            ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/showHomeStay");
            Optional<HomeStay> homeStayOptional = homeStayService.findById(id);
            Iterable<HomeStay> homeStayIterable = homeStayService.findAllByPage(0,6);
            modelAndView.addObject("homeStayByID",homeStayOptional.get());
            modelAndView.addObject("listLienQuan",homeStayIterable);
            modelAndView.addObject("error","Ngày trả phòng phải trước ngày nhập");
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/showInvoice");
            Optional<HomeStay> homeStayOptional = homeStayService.findById(id);
            modelAndView.addObject("homeStay",homeStayOptional.get());
            modelAndView.addObject("invoiceDetails",new InvoiceDetails());
            modelAndView.addObject("account",accountService.finById(accountId).get());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String d1 = dateFormat.format(checkIn);
            String d2 = dateFormat.format(checkOut);

            int totalBillOneDay = (numberDate*homeStayOptional.get().getPrice());
            int totalPrice = totalBillOneDay*numberRoom;
            modelAndView.addObject("checkIn",d1);
            modelAndView.addObject("checkOut",d2);
            modelAndView.addObject("numberDate",numberDate);
            modelAndView.addObject("numberRoom",numberRoom);
            modelAndView.addObject("totalPrice",totalPrice);
            Account a = accountService.finById(accountId).get();
            if(a.getId()==1){
                modelAndView.addObject("invoice",new Invoice(null,null,totalPrice,numberDate,numberRoom,d1,d2));
            }else{
                modelAndView.addObject("invoice",new Invoice(a.getUserName(),a.getEmail(),totalPrice,numberDate,numberRoom,d1,d2));
            }
            return modelAndView;
        }
    }

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @GetMapping("invoice")
    public ModelAndView invoice(@ModelAttribute("invoice")Invoice invoice,
                                @RequestParam("homeStayId")Long homeStayId){
            if(accountId==1){
                Account account = accountService.finById(accountId).get();
                invoiceService.save(invoice);
                InvoiceDetails invoiceDetails= new InvoiceDetails(homeStayService.findById(homeStayId).get(),invoice,account);
                invoiceDetailsService.save(invoiceDetails);
                ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/acceptInvoice");
                modelAndView.addObject("account",account);
                modelAndView.addObject("invoice",invoice);
                modelAndView.addObject("invoiceDetails",invoiceDetails);
                return modelAndView;
            }else {
                Account account = accountService.finById(accountId).get();
                invoiceService.save(invoice);
                InvoiceDetails invoiceDetails = new InvoiceDetails(homeStayService.findById(homeStayId).get(),invoice,account);
                invoiceDetailsService.save(invoiceDetails);
                ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/acceptInvoice");
                modelAndView.addObject("account",account);
                modelAndView.addObject("invoice",invoice);
                modelAndView.addObject("invoiceDetails",invoiceDetails);
                return modelAndView;
            }
    }

    @GetMapping("invoiceAccount")
    public ModelAndView checkInvoide(){
        ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/invoiceAccount");
        modelAndView.addObject("listInvoiceDetails",invoiceDetailsService.listInvoiceByAccount(accountId));
        modelAndView.addObject("account",accountService.finById(accountId).get());
        return modelAndView;
    }
    @GetMapping("invoiceNoAccount")
    public ModelAndView checkNoInvoide(@RequestParam("code")Long code){
        ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/invoiceAccount");
        modelAndView.addObject("listInvoiceDetails",invoiceDetailsService.listInvoiceByInvoice(code));
        modelAndView.addObject("account",accountService.finById(accountId).get());
        return modelAndView;
    }
}
