import { Component, Output, EventEmitter } from '@angular/core'
import { Headers } from '@angular/http';

import { YodleeService } from '../services/yodlee.service';
import { UserProfile } from "../vo/userprofile.vo";
import { YodleeLoginRequest } from "../vo/yodlee.login.request";
import { NetWorth } from "../vo/netWorth.vo";
import { Amount } from "../vo/amount.vo";

@Component({
  selector: 'yodlee-accounts',
  providers: [ YodleeService ],
  templateUrl: 'app/templates/yodlee.accounts.template.html'
})
export class YodleeAccount {

  linked: boolean = false;
  userProfile = new UserProfile();
  netWorth = new NetWorth();
  responseReceived: boolean = false;

  @Output()
  userSessionUpdated = new EventEmitter();

  constructor(private yodleeService: YodleeService) {
    this.linked = false;
    this.responseReceived = false;
    localStorage.removeItem('userSession');
  }

  loginWithYodlee() {

    let user = new YodleeLoginRequest();
    user.user = this.userProfile;
    this.linked = true;
    this.yodleeService.loginToYodlee(JSON.stringify(user))
      .then((result) => {
        localStorage.setItem('userSession', result.user.session.userSession);
        this.userSessionUpdated.emit(null);
        this.displayNetWorth();
      },
      (error) => {
        console.log("Error occurred while linking with Yodlee!" + error.status);
      });
  }

  displayNetWorth() {

    let userSession = localStorage.getItem('userSession');
    let headerParams:Headers = new Headers({});

    if(userSession) {
      headerParams = new Headers({
        'userSession': userSession
      });
    }

    this.yodleeService.getNetWorth(headerParams)
      .then((result) => {

        let am_1 = new Amount();
        am_1 = result.networth[0].asset.amount;
        am_1= result.networth[0].asset.currency;
        this.netWorth.asset = am_1;

        let am_2 = new Amount();
        am_2.amount = result.networth[0].liability.amount;
        am_2.currecncy = result.networth[0].liability.currency;
        this.netWorth.liability = am_2;

        let am_3 = new Amount();
        am_3.amount = result.networth[0].networth.amount;
        am_3.currecncy = result.networth[0].networth.currency;
        this.netWorth.networth = am_3;

        this.netWorth.date = result.networth[0].date;
        this.responseReceived = true;

        console.log("NetWorth: " + JSON.stringify(this.netWorth));
      },
      (error) => {
        console.log("Error: " + error.status);
      });
  }
}
