package codelean.service;

import codelean.model.Account;
import codelean.model.Admin;

import java.util.Optional;

public interface AdminService {
    boolean loginAdmin(String userName,String passWord);
    Iterable<Admin> finByAll();
    Optional<Admin> finById(Long id);
    void save(Admin admin);
    void remove(Long id);
}
