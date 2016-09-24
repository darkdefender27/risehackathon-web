import {Auth} from '../services/auth.service';
import {Component} from '@angular/core'

@Component({
  selector: 'footer-line',
  templateUrl: 'app/templates/footer.template.html'
})
export class FooterComponent {
  constructor(auth: Auth) {
  }
}
