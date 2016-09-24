import { Component } from '@angular/core';
import { ProfileComponent } from './profile.component';
import { FooterComponent } from './footer.component'

@Component({
  selector: 'dashboard',
  templateUrl: 'app/templates/dashboard.mz.html',
  providers: [ProfileComponent, FooterComponent]
})
export class DashBoardComponent {
  constructor() {
    console.log("[INFO]: Material Design Lite Dashboard template loaded.");
  }
}
