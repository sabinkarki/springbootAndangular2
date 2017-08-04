package boot.angular2.api.mock.data;

import boot.angular2.api.domain.Article;
import boot.angular2.api.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;

/**
 * Created by sabin on 8/3/2017.
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DataSetUp implements CommandLineRunner {

    private final ArticleRepository articleRepository;

    @Value("${url}")
    private String url;

    @Override
    public void run(String... strings) throws Exception {
        setUpData(url);
    }

    public void setUpData(String url) throws Exception {
        Resource rsrc = new ClassPathResource(url);
        String path = rsrc.getFile().getAbsolutePath();
        ObjectMapper mapper = new ObjectMapper();
        List<Article> articles = mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(List.class, Article.class));
        this.articleRepository.save(articles);

    }
}
