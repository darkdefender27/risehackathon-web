import { Component } from '@angular/core';
import {OtpService} from "../services/otp.service";
import {Auth} from "../services/auth.service";
import {TransactionVo} from "../vo/transaction.vo";
import {Otp} from "../vo/otp.vo";

@Component({
  selector: 'split-payment',
  providers: [OtpService],
  templateUrl: 'app/templates/split.payment.template.html'
})
export class SplitPayment {

  transactionDetails = new TransactionVo();
  otpSent: boolean = false;

  otpRequest = new Otp();

  transactionCompleted: boolean = false;

  otp: string;
  filledInOtp: string;

  constructor(private authService: Auth, private otpService: OtpService) {
    this.otpSent = false;
    this.otp = "3415";
    this.transactionCompleted = false;
    this.transactionDetails.fromAccount = "";
    this.transactionDetails.toAccount = "";
    this.transactionDetails.amount = "";
    this.transactionDetails.mobileNumber = localStorage.getItem('mobileNumber');
    if(!this.transactionDetails.mobileNumber) {
      this.transactionDetails.mobileNumber = "+919637670413";
    }
  }

  sendOtpConfirmation() {

    this.otpRequest.to_no = this.transactionDetails.mobileNumber;
    this.otpRequest.content = "Your OTP is: " + "3415";

    this.otpService.sendOtpConfirmation(JSON.stringify(this.otpRequest))
      .then((result) => {
        console.log("OTP sent successfully...");
        this.otpSent = true;
      },
      (error) => {
        console.log("Error occurred while sending the OTP!" + error.status);
      });
  }

  confirmOtp() {
    if(this.filledInOtp == this.otp) {
      this.transactionCompleted = true;
      console.log("OTP matched!");
    }
    else {
      console.log("OTP did not match!");
    }
  }
}
