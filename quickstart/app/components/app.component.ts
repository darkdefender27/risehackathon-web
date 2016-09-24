import { Component } from '@angular/core';
import { Auth } from './../services/auth.service';
import { SampleService } from './../services/sample.service';

@Component({
  selector: 'my-app',
  providers: [ Auth, SampleService ],
  templateUrl: 'app/templates/app.template.html'
})
export class AppComponent {

  constructor(private auth: Auth, private sampleService: SampleService) {
  }
}
