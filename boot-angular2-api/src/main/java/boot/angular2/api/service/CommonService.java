package boot.angular2.api.service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sabin on 8/3/2017.
 */
public interface CommonService<T,R> {
    Optional<R> findOne(T t);

     List<R> findAll();

    R create(R r);

    void delete(T t);

    R update(R r);
}
