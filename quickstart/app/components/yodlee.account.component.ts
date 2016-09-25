import { Component, Output, EventEmitter } from '@angular/core'
import { Headers } from '@angular/http';

import { YodleeService } from '../services/yodlee.service';
import { UserProfile } from "../vo/userprofile.vo";
import { YodleeLoginRequest } from "../vo/yodlee.login.request";

@Component({
  selector: 'yodlee-accounts',
  providers: [ YodleeService ],
  templateUrl: 'app/templates/yodlee.accounts.template.html'
})
export class YodleeAccount {

  linked: boolean = false;
  userProfile = new UserProfile();

  @Output()
  userSessionUpdated = new EventEmitter();

  constructor(private yodleeService: YodleeService) {
    this.linked = false;
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
        console.log("Net Worth Result retrieved: " + JSON.stringify(result));
      },
      (error) => {
        console.log("Error: " + error.status);
      });
  }
}
