import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  constructor(private articleService: ArticleService) { }

  //Create form
  articleForm = new FormGroup({
    title: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required)
  });

  ngOnInit() {
     this.getAllArticles();
  }

  //Fetch All articles
  getAllArticles(){
        this.articleService.getAllArticles().subscribe(
          data=>this.allArticles=data,
          errorCode =>  this.statusCode = errorCode);
  }

}
