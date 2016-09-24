import { Injectable }    from '@angular/core';
import { Headers } from '@angular/http';

import { HttpService } from './http.service';
import { URI } from './../config/uri.config';
import { HttpRequestType } from "../config/http.request.type";

@Injectable()
export class YodleeService {

  constructor(private httpService: HttpService) {
  }

  loginToYodlee(payLoad: string): Promise<any> {
    return this.httpService.executeHttpRequest(URI.YODLEE_LOGIN, payLoad, HttpRequestType.POST);
  }

  getYodleeAccountDetails(headers: Headers): Promise<any> {
    return this.httpService.executeRequest(URI.YODLEE_ACCOUNT, HttpRequestType.GET, headers);
  }

  getNetWorth(headers: Headers): Promise<any> {
    return this.httpService.executeRequest(URI.YODLEE_NET_WORTH, HttpRequestType.GET, headers);
  }
}
