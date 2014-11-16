package meanMCQ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by red on 11/16/14.
 */
public interface DataRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {
    Optional<T> findById(Long ID);
}
