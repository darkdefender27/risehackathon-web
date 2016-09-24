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
    this.sampleService.getSampleMessage()
      .then((result) => {
          console.log("Result retrieved: " + JSON.stringify(result));
      },
      (error) => {
        console.log("Error: " + error.status);
      });
  }
}
