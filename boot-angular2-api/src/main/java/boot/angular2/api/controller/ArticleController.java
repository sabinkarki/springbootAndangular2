package boot.angular2.api.controller;

import boot.angular2.api.domain.Article;
import boot.angular2.api.exception.NotFoundException;
import boot.angular2.api.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by sabin on 8/3/2017.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("article")
    public ResponseEntity<Article> getArticleById(@RequestParam("id") String id) {
        Optional<Article> article = articleService.findOne((Integer.parseInt(id)));
        return article.map(article1 -> {
            return new ResponseEntity<Article>(article1, HttpStatus.OK);
        }).orElseThrow(() -> new NotFoundException("Not found"));
    }
    @GetMapping("all-articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleService.findAll();
        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
    }
    @PostMapping("article")
    public ResponseEntity<Void> createArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.create(article);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article?id={id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.update(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @DeleteMapping("article")
    public ResponseEntity<Void> deleteArticle(@RequestParam("id") String id) {
        articleService.delete(Integer.parseInt(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
