import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule }   from '@angular/forms';
import { AUTH_PROVIDERS } from 'angular2-jwt';

import { AppComponent }  from './components/app.component';
import { HomeComponent } from './components/home.component';
import { routing, appRoutingProviders } from './routes/app.routes';
import { ProfileComponent } from './components/profile.component';
import { DashBoardComponent } from './components/dashboard.component';
import { FooterComponent } from './components/footer.component';
import { HttpService } from './services/http.service';
import { SplitPayment } from './components/splitpayment.component';
import { YodleeAccount } from './components/yodlee.account.component';
import { YodleeService } from './services/yodlee.service';
import { YodleeAccountAggregator } from './components/yodlee.aggregate.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    DashBoardComponent,
    YodleeAccount,
    YodleeAccountAggregator,
    SplitPayment,
    FooterComponent
  ],
  providers: [
    appRoutingProviders,
    YodleeService,
    HttpService,
    AUTH_PROVIDERS
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    routing
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
