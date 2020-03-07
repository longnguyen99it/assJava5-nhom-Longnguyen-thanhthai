package codelean.service;

import codelean.model.Account;
import codelean.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account findByEmailAndPassWord(String email, String pass) {
        String query = "select ac from Account ac where ac.email = '"+email+"' and ac.passWord = '"+pass+"'";
        TypedQuery<Account> accountTypedQuery = entityManager.createQuery(query,Account.class);
        return accountTypedQuery.getSingleResult();
    }

    @Override
    public Iterable<Account> finByAll() {
        String query = "select ac from Account ac order by ac.id desc";
        TypedQuery<Account> accountTypedQuery = entityManager.createQuery(query,Account.class);
        return accountTypedQuery.getResultList();
    }

    @Override
    public Optional<Account> finById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        accountRepository.deleteById(id);
    }
}
