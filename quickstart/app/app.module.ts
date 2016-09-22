import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AUTH_PROVIDERS } from 'angular2-jwt';

import { AppComponent }  from './components/app.component';
import { HomeComponent } from './components/home.component';
import { routing, appRoutingProviders } from './routes/app.routes';
import { ProfileComponent } from './components/profile.component';
import { DashBoardComponent } from './components/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    DashBoardComponent
  ],
  providers: [
    appRoutingProviders,
    AUTH_PROVIDERS
  ],
  imports: [
    BrowserModule,
    routing
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
