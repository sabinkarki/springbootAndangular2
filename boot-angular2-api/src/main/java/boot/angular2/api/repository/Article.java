package boot.angular2.api.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by sabin on 8/3/2017.
 */
public interface Article extends CrudRepository<Article,Integer> {
}
