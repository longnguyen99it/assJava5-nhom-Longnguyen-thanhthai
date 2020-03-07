package codelean.service;

import codelean.model.Admin;
import codelean.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean loginAdmin(String userName, String passWord) {
        String query = "select a from Admin a where a.userName = '"+userName+"' and a.passWord = '"+passWord+"'";
        try {
            TypedQuery<Admin> adminTypedQuery = entityManager.createQuery(query,Admin.class);
            if(adminTypedQuery.getResultList().size()==0){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }



    }

    @Override
    public Iterable<Admin> finByAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> finById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void remove(Long id) {
        adminRepository.deleteById(id);
    }
}
