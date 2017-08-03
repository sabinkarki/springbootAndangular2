package boot.angular2.api.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sabin on 8/3/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="articles")
public class Article implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int articleId;
    private String title;
    private String category;
}
