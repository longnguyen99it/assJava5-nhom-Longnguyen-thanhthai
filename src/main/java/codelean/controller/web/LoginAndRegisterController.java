package codelean.controller.web;

import codelean.model.Account;
import codelean.model.HomeStay;
import codelean.service.AccountService;
import codelean.service.HomeStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(name = "LoginView/")
public class LoginAndRegisterController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HomeStayService homeStayService;
    @RequestMapping("loginForm")
    public ModelAndView loginAccount(){
        ModelAndView modelAndView = new ModelAndView("LoginView/login");
        modelAndView.addObject("account",new Account());
        return modelAndView;
    }
    @PostMapping("login")
    public ModelAndView dangnhaptaikhoan(@RequestParam("email")String email,@RequestParam("pass")String pass){
        Account account = accountService.findByEmailAndPassWord(email,pass);
        if(account != null){
            ModelAndView modelAndView = new ModelAndView("PolyProHomeStay/homePage");
            modelAndView.addObject("account",account);
            List<HomeStay> list = homeStayService.findAllPageById(0,6,"id");
            modelAndView.addObject("listHomeStayByIdDesc", list);
            ControlleHome.accountId =account.getId();
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("LoginView/login");
            modelAndView.addObject("mesage","tài khoản hoặc mật khẩu k chính xác");
            return modelAndView;
        }
    }
//    @GetMapping("logoff")
//    public ModelAndView logOff(){
//        return
//    }
    @GetMapping("addAccount")
    public ModelAndView addAccountFrom(){
        ModelAndView modelAndView = new ModelAndView("LoginView/addAccount");
        modelAndView.addObject("accounts",new Account());
        return modelAndView;
    }
    @PostMapping("addAccount")
    public ModelAndView addAccount(@ModelAttribute("accounts")Account account){
        accountService.save(account);
        ModelAndView modelAndView = new ModelAndView("LoginView/addAccount");
        modelAndView.addObject("accounts",new Account());
        modelAndView.addObject("message","đăng ký thành công");
        return modelAndView;
    }
}
