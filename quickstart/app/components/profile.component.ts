import { Component, OnInit } from '@angular/core';

import { Auth } from './../services/auth.service';
import { YodleeService } from '../services/yodlee.service';

@Component({
  selector: 'profile',
  providers: [ YodleeService ],
  templateUrl: './app/templates/profile.template.html'
})
export class ProfileComponent implements OnInit {

  constructor(private auth: Auth, private yodleeService: YodleeService) {
  }

  ngOnInit() {
  }
}
