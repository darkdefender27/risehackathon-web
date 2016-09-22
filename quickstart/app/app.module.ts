import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AUTH_PROVIDERS } from 'angular2-jwt';

import { AppComponent }  from './app.component';
import { HomeComponent } from './home.component';
import { routing, appRoutingProviders } from './app.routes';
import { ProfileComponent } from './profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent
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
