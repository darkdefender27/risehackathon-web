import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { HttpRequestType } from  './../config/http.request.type';

@Injectable()
export class HttpService {

  constructor(private http: Http) {
  }

  executeHttpRequest(url:string, payLoad:any = null, requestType:string = HttpRequestType.GET):Promise<any> {
    return new Promise((resolve, reject)=> {

      let headers = new Headers(
        {
          'Content-Type': "application/json;charset=UTF-8",
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
          'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
        }
      );

      let options = new RequestOptions({headers: headers});
      var body:string = typeof (payLoad) == "string" ? payLoad : JSON.stringify(payLoad);

      switch (requestType) {
        case HttpRequestType.GET:
          this.http.get(url, options)
            .subscribe(
              result => {
                if((<Response>result).text()!=null && (<Response>result).text()!= '') {
                  resolve((<Response>result).json());
                }else {
                  resolve(null);
                }
              },
              error=> {
                console.log(error);
                reject(error);
              }
            );
          break;
        case HttpRequestType.POST:
          this.http.post(url, body, options)
            .subscribe(
              result => {
                if((<Response>result).text()!=null && (<Response>result).text()!= '') {
                  resolve((<Response>result).json());
                }else {
                  resolve(null);
                }
              },
              error=> {
                console.log(error);
                reject(error);
              }
            );
          break;

      }
    });
  }
}
