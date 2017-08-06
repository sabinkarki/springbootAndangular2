import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Article} from "./article";
import {ArticleService} from "../services/article.service";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  //Component properties
  allArticles: Article[];
  statusCode: number;
  requestProcessing = false;
  articleIdToUpdate = null;
  processValidation = false;

  //Create constructor to get service instance
  constructor(private articleService: ArticleService) {
  }

  //Create form
  articleForm = new FormGroup({
    title: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required)
  });

  ngOnInit() {
    this.getAllArticles();
  }

  //Handle create and update article
  onArticleFormSubmit() {
    this.processValidation = true;
    let title = this.articleForm.get('title').value.trim();
    let category = this.articleForm.get('category').value.trim();
    if (this.articleForm.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.preProcessConfigurations();
    if (this.articleIdToUpdate === null) {
      //Handle create article
      let article = new Article(null, title, category);
      this.articleService.createArticle(article)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllArticles();
            this.backToCreateArticle();
          },
          errorCode => this.statusCode = errorCode);
    }
    else {

      //Handle update article
      let article = new Article(this.articleIdToUpdate, title, category);
      this.articleService.updateArticle(article)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllArticles();
            this.backToCreateArticle();
          },
          errorCode => this.statusCode = errorCode);

    }
  }

  //Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }

  //Go back from update to create
  backToCreateArticle() {
    this.articleIdToUpdate = null;
    this.articleForm.reset();
    this.processValidation = false;
  }


  //Fetch All articles
  getAllArticles() {
    this.articleService.getAllArticles().subscribe(
      data => this.allArticles = data,
      errorCode => this.statusCode = errorCode);
  }

  //Load article by id to edit
  loadArticleToEdit(articleId: string) {
    this.preProcessConfigurations();
    this.articleService.getArticleById(articleId)
      .subscribe(article => {
          this.articleIdToUpdate = article.articleId;
          this.articleForm.setValue({title: article.title, category: article.category});
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode => this.statusCode = errorCode);
  }
}
