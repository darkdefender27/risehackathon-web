import { Component } from '@angular/core';


@Component({
  selector: 'dashboard',
  templateUrl: 'app/templates/dashboard.mz.html'
})
export class DashBoardComponent {
  constructor() {
    console.log("[INFO]: Material Design Lite Dashboard template loaded.");
  }
}
