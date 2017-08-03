package boot.angular2.api.repository;

import boot.angular2.api.domain.Article;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sabin on 8/3/2017.
 */
public interface ArticleRepository extends CrudRepository<Article,Integer> {
}
