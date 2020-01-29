import { Component } from '@angular/core';
import { SharedService } from './shared.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  credentials: Object;

  constructor(private _sharedService: SharedService) {

    _sharedService.changeEmitted$.subscribe(
      item => {
        this.credentials = item;
      }
    )
    
  }
}
