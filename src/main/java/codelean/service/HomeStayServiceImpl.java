package codelean.service;

import codelean.model.HomeStay;
import codelean.repository.HomeStayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HomeStayServiceImpl implements HomeStayService {

    @Autowired
    private HomeStayRepository homeStayRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void deleteHomeStay(String status,Long id) {
        String query = "update HomeStay hs set hs.status = '"+status+"' where hs.id = '"+id+"'";
        TypedQuery<HomeStay> homeStayTypedQuery = entityManager.createQuery(query,HomeStay.class);
    }

    @Override
    public List<HomeStay> findAllPageById(int page, int size, String sort) {
        Sort sortkey = Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page,size,sortkey);
        return homeStayRepository.findAll(pageable).getContent();
    }

    @Override
    public List<HomeStay> findAllByPage(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return homeStayRepository.findAll(pageable).getContent();
    }

    @Override
    public List<HomeStay> finByAddress(String address) {
        String query = "select hs from HomeStay hs where hs.address like '%"+address+"%'";
        TypedQuery<HomeStay> homeStayTypedQuery = entityManager.createQuery(query,HomeStay.class);
        return homeStayTypedQuery.getResultList();
    }



    @Override
    public Iterable<HomeStay> findAll() {
        return homeStayRepository.findAll();
    }

    @Override
    public Optional<HomeStay> findById(Long id) {
        return homeStayRepository.findById(id);
    }

    @Override
    public void save(HomeStay homeStay) {
        homeStayRepository.save(homeStay);
    }

    @Override
    public void remove(Long id) {
        homeStayRepository.deleteById(id);
    }



}
