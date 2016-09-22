import { Component }  from '@angular/core';
import { Auth }       from './../services/auth.service';
import {DashBoardComponent} from "./dashboard.component";

@Component({
  selector: 'home',
  templateUrl: './app/templates/home.template.html',
  providers: [DashBoardComponent]
})

export class HomeComponent {
  constructor(private auth: Auth) {}
};
