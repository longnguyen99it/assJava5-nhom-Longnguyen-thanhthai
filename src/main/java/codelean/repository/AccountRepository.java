package codelean.repository;

import codelean.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

}
