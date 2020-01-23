import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpService } from '../http.service';

declare const M;

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.scss']
})
export class ManageComponent implements OnInit, AfterViewInit {

  constructor(private _http: HttpService) { }

  data = {}

  ngOnInit() {
    this._http.getRequest('http://localhost:8080/tosad-api/restservices/define/').subscribe(rdata => {
      this.data = rdata
    })
  }

  ngAfterViewInit(): void {
    this.refresh()
  }

  // Example data
  triggers: { [key: string]: Object } = {
    'trigger t1': {
      'rules': [
        'attribute a1',
        'attribute a2'
      ]
    },
    'trigger t2': {
      'rules': [
        'attribute b2'
      ]
    }
  }

  rules = []
  properties = []

  getRules(triggerName) {
    this.properties = []
    this.rules = []
    for (const item in this.triggers[triggerName]['rules']) {
      let rule = {'name': this.triggers[triggerName]['rules'][item]}
      this.rules.push(rule)
    }
  }

  getProperties(ruleName) {
    this.properties = [
      {
        'name': ruleName
      }
    ]
  }

  runGenerate(triggerName) {
    console.log(triggerName)
  }

  refresh() {
    setTimeout(() => {
      M.AutoInit()
    }, 10)
  }
    
}
