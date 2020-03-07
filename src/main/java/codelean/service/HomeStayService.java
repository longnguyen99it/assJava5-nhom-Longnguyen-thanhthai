package codelean.service;


import codelean.model.HomeStay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface HomeStayService {
    void deleteHomeStay(String status, Long id);
    List<HomeStay> findAllPageById(int page,int size,String sort);
    List<HomeStay> findAllByPage(int page,int size);
    List<HomeStay> finByAddress(String address);
    Iterable<HomeStay> findAll();
    Optional<HomeStay> findById(Long id);
    void save(HomeStay homeStay);
    void remove(Long id);
}
