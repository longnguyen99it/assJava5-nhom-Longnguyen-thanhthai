package codelean.controller.admin;

import codelean.model.*;
import codelean.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/")
public class adminController {
    @Autowired
    private HomeStayService homeStayService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AccountService accountService;

    static String user=null;
    @GetMapping("/")
    public ModelAndView showLoginAdmin(){
        ModelAndView modelAndView = new ModelAndView("admin/login");
        modelAndView.addObject("admin",new Admin());
        return modelAndView;
    }
    @GetMapping("showlist")
    public ModelAndView showlist(@RequestParam(name = "page",defaultValue = "0")int page){
        ModelAndView modelAndView = new ModelAndView("admin/list");
        List<HomeStay> list = homeStayService.findAllPageById(page,6,"id");
        modelAndView.addObject("listHomeStay",list);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @PostMapping("loginAdmin")
    public ModelAndView loginAdmin(@ModelAttribute("admin")Admin admin){

        if(adminService.loginAdmin(admin.getUserName(),admin.getPassWord())==true){
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/showlist");
            user=admin.getUserName();
            return modelAndView;
        }else{
            ModelAndView modelAndView1 = new ModelAndView("admin/login");
            modelAndView1.addObject("admin",admin);
            modelAndView1.addObject("message","Đăng nhập thất bại");
            return modelAndView1;
        }
    }

    @GetMapping("creatHomeStay")
    public ModelAndView createHomStayForm(){
        ModelAndView modelAndView = new ModelAndView("admin/create");
        modelAndView.addObject("homeStay",new HomeStay());
        return modelAndView;
    }

    @PostMapping("creatHomeStay")
    public ModelAndView createHomStay(@ModelAttribute("homeStay")HomeStay homeStay){
        ModelAndView modelAndView = new ModelAndView("admin/create");
        homeStayService.save(homeStay);
        modelAndView.addObject("homeStay",new HomeStay());
        modelAndView.addObject("message","tạo thành công");
        return modelAndView;
    }

    @GetMapping("editHomeStay/{id}")
    public ModelAndView showFormEditHomeStay(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/edit");
        modelAndView.addObject("homeStayID",homeStayService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("editHomeStay")
    public ModelAndView editHomeStay(@ModelAttribute("homeStayID")HomeStay homeStay){
        ModelAndView modelAndView = new ModelAndView("admin/edit");
        homeStayService.save(homeStay);
        modelAndView.addObject("message","cập nhập thành công");
        return modelAndView;
    }

    @GetMapping("deleteHomeStay/{id}")
    public ModelAndView deleteHomeStayFrom(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/delete");
        modelAndView.addObject("homestayIdDelete",homeStayService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("deleteHomeStay")
    public ModelAndView deleteHomeStayId(@RequestParam("id")Long id){
        ModelAndView modelAndView = new ModelAndView("admin/delete");
        homeStayService.deleteHomeStay("delete",id);
        return modelAndView;
    }

    @GetMapping("showInvoice")
    public ModelAndView showInvoice(){
        ModelAndView modelAndView = new ModelAndView("admin/invoice");
        List<Invoice> list = invoiceService.showInvoiceAdmin();
        modelAndView.addObject("listInvoice",list);
        return modelAndView;
    }

    @GetMapping("invoiceDetailsLG")
    public ModelAndView invoiceDetails(@RequestParam("email") String email,@RequestParam("name")String name){
        ModelAndView modelAndView = new ModelAndView("admin/invoicedetailslogin");
        List<InvoiceDetails> invoiceDetailsList = invoiceDetailsService.showInvoiceDetailsAdmin(email,name);
        modelAndView.addObject("listInvoiceDetails",invoiceDetailsList);
        modelAndView.addObject("email",email);
        modelAndView.addObject("name",name);
        return modelAndView;
    }


    @GetMapping("showAccount")
    public ModelAndView showAccountForm(){
        ModelAndView modelAndView = new ModelAndView("admin/account");
        modelAndView.addObject("listAccountKh",accountService.finByAll());
        return modelAndView;
    }
    @GetMapping("showAccountEdit/{id}")
    public ModelAndView editAccountForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/edit-account");
        Account account = accountService.finById(id).get();
        modelAndView.addObject("accountEdit",new Account(account.getUserName(),account.getPassWord(),account.getEmail()));
        return modelAndView;
    }
    @GetMapping("AccountEdit")
    public ModelAndView editAccount(@ModelAttribute("accountKh")Account account){
        ModelAndView modelAndView = new ModelAndView("admin/edit-account");
        accountService.save(account);
        modelAndView.addObject("accountEdit",new Account());
        modelAndView.addObject("message","cập nhập thành công");
        return modelAndView;
    }

    @GetMapping("addAccount")
    public ModelAndView addAccountFrom12(){
        ModelAndView modelAndView = new ModelAndView("admin/addAccount");
        modelAndView.addObject("accounts",new Account());
        return modelAndView;
    }

    @PostMapping("addAccount")
    public ModelAndView addAccount21(@ModelAttribute("accounts")Account account){
        accountService.save(account);
        ModelAndView modelAndView = new ModelAndView("admin/addAccount");
        modelAndView.addObject("accounts",new Account());
        modelAndView.addObject("message","đăng ký thành công");
        return modelAndView;
    }
}
