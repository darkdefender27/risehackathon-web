import { Component } from '@angular/core';
import { ProfileComponent } from './profile.component';
import { FooterComponent } from './footer.component'
import { YodleeAccount } from './yodlee.account.component';
import { YodleeAccountAggregator } from "./yodlee.aggregate.component";

@Component({
  selector: 'dashboard',
  templateUrl: 'app/templates/dashboard.mz.html',
  providers: [ProfileComponent, YodleeAccountAggregator, YodleeAccount, FooterComponent]
})
export class DashBoardComponent {

  userSession: string;
  linked: boolean = false;

  constructor() {
    this.userSession = localStorage.getItem('userSession');
  }

  updateUserSession() {
    this.userSession = localStorage.getItem('userSession');
    this.linked = true;
  }
}
