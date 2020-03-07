package codelean.service;

import codelean.model.Account;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public interface AccountService {
    Account findByEmailAndPassWord(String email,String pass);
    Iterable<Account> finByAll();
    Optional<Account> finById(Long id);
    void save(Account mode);
    void remove(Long id);
}
