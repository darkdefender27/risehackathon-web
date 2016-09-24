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

  constructor(private yodleeService: YodleeService) {
  }

  ngOnChanges() {
    let headerParams:Headers = new Headers({});
    if(this.userSession) {
      headerParams = new Headers({
        'userSession': this.userSession
      });

      this.yodleeService.getYodleeAccountDetails(headerParams)
        .then((result) => {
            this.populateAccountDetails(result);
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
        let amountDetails: Amount = new Amount();

        amountDetails.amount = s.availableCash.amount;
        amountDetails.currecncy = s.availableCash.currecncy;
        yodleeAccount.availableCash = amountDetails;

        amountDetails.amount = s.availableCredit.amount;
        amountDetails.currecncy = s.availableCredit.currecncy;
        yodleeAccount.availableCredit = amountDetails;

        amountDetails.amount = s.balance.amount;
        amountDetails.currecncy = s.balance.currecncy;
        yodleeAccount.balance = amountDetails;

        amountDetails.amount = s.totalCashLimit.amount;
        amountDetails.currecncy = s.totalCashLimit.currecncy;
        yodleeAccount.totalCashLimit = amountDetails;

        amountDetails.amount = s.totalCreditLine.amount;
        amountDetails.currecncy = s.totalCreditLine.currecncy;
        yodleeAccount.totalCreditLine = amountDetails;
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

    console.log("All Accounts: " + this.yodleeAccounts);
  }

}
