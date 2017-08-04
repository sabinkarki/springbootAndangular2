package boot.angular2.api.service.serviceImpl;

import boot.angular2.api.domain.Article;
import boot.angular2.api.repository.ArticleRepository;
import boot.angular2.api.service.ArticleService;
import boot.angular2.api.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sabin on 8/3/2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ArticleServiceIml implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Optional<Article> findOne(Integer id) {

        return Optional.ofNullable(this.articleRepository.findOne(id));
    }

    @Override
    public List<Article> findAll() {
        return (List<Article>)this.articleRepository.findAll();
    }

    @Override
    public synchronized boolean create(Article article) {

        if ( this.articleRepository.findByTileAAndCategory(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            this.articleRepository.save(article);
            return true;
        }
    }

    @Override
    public void delete(Integer id) {
        Article article=this.articleRepository.findOne(id);
        this.articleRepository.delete(article);
    }

    @Override
    public void update(Article article) {
        Article artcl=this.articleRepository.findOne(article.getArticleId());
        BeanUtils.copyProperties(article, artcl);
         this.articleRepository.save(artcl);
    }
}
