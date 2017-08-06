import {Injectable} from "@angular/core";
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import {Observable} from "rxjs/Rx";
import {Article} from "../article/article";

@Injectable()
export class ArticleService {

  //URLs for CRUD operations
  allArticlesUrl = "http://localhost:8080/user/all-articles";
  articleUrl = "http://localhost:8080/user/article";

  //Create constructor to get Http instance
  constructor(private http:Http){}

  //Fetch all articles
  getAllArticles():Observable<Article[]>{
    return this.http.get(this.allArticlesUrl).map(this.extractData).catch(this.handleError);
  }

  //success
  private extractData(res: Response) {
    let body = res.json();
    return body;
  }

  //Error
  private handleError (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.status);
  }
}
