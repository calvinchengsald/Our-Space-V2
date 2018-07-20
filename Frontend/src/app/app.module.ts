import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {CookieModule } from 'ngx-cookie';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule, MatSelectModule, MatCardModule, MatButtonModule, MatFormFieldModule, MatIconModule} from '@angular/material';


import { LoginService } from './services/login.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterComponent } from './register/register.component';
import { PostService } from './services/post.service';
import { MessegeComponent } from './messege/messege.component';
import { MessegeModelService } from './services/messege-model.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginPageComponent,
    RegisterComponent,
    MessegeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule, MatSelectModule,
    MatCardModule, MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    HttpClientModule,
    CookieModule.forRoot(),


    RouterModule.forRoot([
      { path: 'home', component: HomeComponent },
      { path: 'login', component: LoginPageComponent },
      { path: 'register', component: RegisterComponent },
      { path: '', component: HomeComponent, pathMatch: 'full' },
      { path: '**', component: HomeComponent, pathMatch: 'full' },
    ])
  ],
  providers: [LoginService, PostService, MessegeModelService],
  bootstrap: [AppComponent],

})
export class AppModule { }
