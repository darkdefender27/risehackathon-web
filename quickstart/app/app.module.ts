import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { AUTH_PROVIDERS } from 'angular2-jwt';

import { AppComponent }  from './components/app.component';
import { HomeComponent } from './components/home.component';
import { routing, appRoutingProviders } from './routes/app.routes';
import { ProfileComponent } from './components/profile.component';
import { DashBoardComponent } from './components/dashboard.component';
import { FooterComponent } from './components/footer.component';
import { HttpService } from './services/http.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    DashBoardComponent,
    FooterComponent
  ],
  providers: [
    appRoutingProviders,
    HttpService,
    AUTH_PROVIDERS
  ],
  imports: [
    BrowserModule,
    HttpModule,
    routing
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
