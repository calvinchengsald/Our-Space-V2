import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule }   from '@angular/forms';
import {RouterModule, Router} from '@angular/router';

import { LoginService } from './services/login.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginPageComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path: 'login', component:LoginPageComponent},
      {path: 'register', component:RegisterComponent},
      {path: '', component:HomeComponent,pathMatch:'full'},
      {path: '**', component:HomeComponent,pathMatch:'full'},
    ])
  ],
  providers: [LoginService],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
