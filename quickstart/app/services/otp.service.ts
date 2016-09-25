import { Injectable }    from '@angular/core';
import { Headers } from '@angular/http';

import { HttpService } from './http.service';
import { URI } from './../config/uri.config';
import { HttpRequestType } from "../config/http.request.type";

@Injectable()
export class OtpService {

  constructor(private httpService: HttpService) {
  }

  sendOtpConfirmation(payLoad: string): Promise<any> {
    return this.httpService.executeHttpRequest(URI.SEND_OTP_AUTHENTICATION, payLoad, HttpRequestType.POST);
  }
}
