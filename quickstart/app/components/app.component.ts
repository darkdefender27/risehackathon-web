import { Component } from '@angular/core';
import { Auth } from './../services/auth.service';

@Component({
  selector: 'my-app',
  providers: [ Auth ],
  templateUrl: 'app/templates/app.template.html'
})
export class AppComponent {
  constructor(private auth: Auth) {}
}
