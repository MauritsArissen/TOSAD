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
      "rules": [
        "attribute a1",
        "attribute a2"
      ]
    },
    "trigger t2": {
      "rules": [
        "attribute b2"
      ]
    }
  }

  rules = []
  properties = []

  getRules(triggerName) {
    this.properties = []
    this.rules = []
    for (const item in this.triggers[triggerName]["rules"]) {
      let rule = {"name": this.triggers[triggerName]["rules"][item]}
      this.rules.push(rule)
    }
  }

  getProperties(ruleName) {
    this.properties = [
      {
        "name": ruleName
      }
    ]
  }
    
}
