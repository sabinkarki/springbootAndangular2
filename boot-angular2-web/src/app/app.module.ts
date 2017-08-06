import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
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
  //Inorder to use ReactiveForm Module inject in app module
  imports: [
    BrowserModule,
    HttpModule,
    ReactiveFormsModule
  ],
  /*List of Services*/
  providers: [ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
