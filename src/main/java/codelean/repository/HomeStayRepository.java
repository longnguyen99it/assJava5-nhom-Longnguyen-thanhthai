package codelean.repository;

import codelean.model.HomeStay;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeStayRepository extends PagingAndSortingRepository<HomeStay,Long> {

}
