import { Injectable }    from '@angular/core';
import { HttpService } from './http.service';
import { URI } from './../config/uri.config';

@Injectable()
export class SampleService {

  constructor(private httpService: HttpService) {
  }

  getSampleMessage(): Promise<any> {
    return this.httpService.executeHttpRequest(URI.TSDB);
  }
}
