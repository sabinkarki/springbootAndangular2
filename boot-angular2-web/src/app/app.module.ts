import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ArticleComponent } from './article/article.component';
import {HttpModule} from "@angular/http";
import {ArticleService} from "./services/article.service";

//Inorder to use http module we have to inject in app.module.ts
@NgModule({

  /*Respective  Component*/
  declarations: [
    AppComponent,
    ArticleComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  /*List of Services*/
  providers: [ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
