import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CookieModule } from 'ngx-cookie';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatSelectModule, MatCardModule, MatButtonModule, MatFormFieldModule, MatIconModule } from '@angular/material';


import { LoginService } from './services/login.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterComponent } from './pages/register/register.component';
import { PostService } from './services/post.service';
import { ProfileComponent } from './pages/profile/profile.component';
import { ProfileService } from './services/profile.service';
import { MessegeComponent } from './components/messege/messege.component';
import { MessegeModelService } from './services/messege-model.service';
import { ResetPageComponent } from './reset-page/reset-page.component';
import { PostComponent } from './components/post/post.component';
import { UploadFileService } from './services/upload-file.service';
import { UsersComponent } from './pages/users/users.component';
import { SafePipe } from './services/youtube.pipe';
import { ActivationComponent } from './pages/activation/activation.component';
import { GamePageComponent } from './pages/game-page/game-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginPageComponent,
    RegisterComponent,
    ProfileComponent,
    MessegeComponent,
    PostComponent,
    ResetPageComponent,
    UsersComponent,
    SafePipe,
    ActivationComponent,
    GamePageComponent
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
      { path: 'home', component: HomeComponent},
      { path: 'allusers', component: UsersComponent },
      { path: 'login', component: LoginPageComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'profile/:email', component: ProfileComponent },
      { path: 'activation', component: ActivationComponent },
      { path: 'reset', component: ResetPageComponent },
      { path: 'game', component: GamePageComponent },
      { path: '', component: LoginPageComponent, pathMatch: 'full' },
      { path: '**', component: LoginPageComponent, pathMatch: 'full' },
    ])
  ],
  providers: [LoginService, PostService, MessegeModelService, ProfileService, UploadFileService],
  bootstrap: [AppComponent],

})
export class AppModule { }
