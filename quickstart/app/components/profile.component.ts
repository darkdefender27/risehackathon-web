import { Auth } from './../services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'profile',
  templateUrl: './app/templates/profile.template.html'
})
export class ProfileComponent {
  constructor(private auth: Auth) {
    console.log("[DEBUG] Profile component loaded.");
  }
}
