import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.scss']
})
export class ManageComponent implements OnInit {

  constructor(private _http: HttpService) { }

  ngOnInit() {
  }

  // Example data
  triggers: { [key: string]: Object } = {
    "trigger t1": {
      "rules": "attribute a1"
    },
    "trigger t2": {
      "rules": "attribute a2"
    }
  }

  rules = []

  getRules(triggerName) {
    this.rules = [
      {
        "name": this.triggers[triggerName]["rules"]
      }
    ]
  }
    
}
