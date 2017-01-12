package uzsy.download.john;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "crime", path = "crime")
public interface CrimeRepository extends PagingAndSortingRepository<Crime, Long> {

   List<Crime> findByTitle(@Param("title") String title);

}
