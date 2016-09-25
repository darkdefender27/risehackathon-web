import { Component, OnChanges, Input } from '@angular/core'
import { Headers } from '@angular/http';

import { YodleeService } from '../services/yodlee.service';
import { UserProfile } from "../vo/userprofile.vo";
import { YodleeLoginRequest } from "../vo/yodlee.login.request";
import { YodleeAccountDetails } from "../vo/yodlee.account.details";
import { Amount } from "../vo/amount.vo";

@Component({
  selector: 'yodlee-aggregate',
  providers: [ YodleeService ],
  templateUrl: 'app/templates/yodlee.aggregate.template.html'
})
export class YodleeAccountAggregator implements OnChanges {

  yodleeAccounts: Array<YodleeAccountDetails>;
  @Input()
  userSession: string;
  ready: boolean = false;

  yodleeAccount_1: YodleeAccountDetails = new YodleeAccountDetails();
  yodleeAccount_2: YodleeAccountDetails = new YodleeAccountDetails();
  yodleeAccount_3: YodleeAccountDetails = new YodleeAccountDetails();

  constructor(private yodleeService: YodleeService) {
  }

  ngOnChanges() {
    let headerParams:Headers = new Headers ({});
    if(this.userSession) {
      headerParams = new Headers({
        'userSession': this.userSession
      });

      this.yodleeService.getYodleeAccountDetails(headerParams)
        .then((result) => {
            this.populateAccountDetails(result);
            this.ready = true;
          },
          (error) => {
            console.log("Error: " + error.status);
          });
    }
  }

  private populateAccountDetails(result) {

    this.yodleeAccounts = [];

    result.account.forEach((s) => {
      let yodleeAccount = new YodleeAccountDetails();

      yodleeAccount.container = s.CONTAINER;
      yodleeAccount.accountName = s.accountName;
      yodleeAccount.accountNumber = s.accountNumber;
      yodleeAccount.accountStatus = s.accountStatus;

      if(yodleeAccount.container == "creditCard") {
        let amountDetails_1: Amount = new Amount();

        amountDetails_1.amount = s.availableCash.amount;
        amountDetails_1.currecncy = s.availableCash.currecncy;
        yodleeAccount.availableCash = amountDetails_1;

        let amountDetails_2: Amount = new Amount();

        amountDetails_2.amount = s.balance.amount;
        amountDetails_2.currecncy = s.balance.currecncy;
        yodleeAccount.balance = amountDetails_2;

        let amountDetails_3: Amount = new Amount();

        amountDetails_3.amount = s.availableCredit.amount;
        amountDetails_3.currecncy = s.availableCredit.currecncy;
        yodleeAccount.availableCredit = amountDetails_3;

        let amountDetails_4: Amount = new Amount();

        amountDetails_4.amount = s.totalCashLimit.amount;
        amountDetails_4.currecncy = s.totalCashLimit.currecncy;
        yodleeAccount.totalCashLimit = amountDetails_4;

        let amountDetails_5: Amount = new Amount();

        amountDetails_5.amount = s.totalCreditLine.amount;
        amountDetails_5.currecncy = s.totalCreditLine.currecncy;
        yodleeAccount.totalCreditLine = amountDetails_5;
      }
      else if (yodleeAccount.container == "bank") {
        let amountDetails: Amount = new Amount();

        amountDetails.amount = s.availableBalance.amount;
        amountDetails.currecncy = s.availableBalance.currecncy;
        yodleeAccount.availableCash = amountDetails;

        amountDetails.amount = s.balance.amount;
        amountDetails.currecncy = s.balance.currecncy;
        yodleeAccount.balance = amountDetails;
      }

      this.yodleeAccounts.push(yodleeAccount);

    });

    console.log("All Accounts: " + JSON.stringify(this.yodleeAccounts));

    this.yodleeAccount_1 = this.yodleeAccounts[0];
    this.yodleeAccount_2 = this.yodleeAccounts[1];
    this.yodleeAccount_3 = this.yodleeAccounts[2];

    console.log("All Accounts: " + JSON.stringify(this.yodleeAccounts));
  }

}
