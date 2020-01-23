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

  triggers = []
  rules = []
  properties = []

  ngOnInit() {
    this._http.getRequest('http://localhost:8080/tosad-api/restservices/generate/').subscribe(data => {
      for (const item in data) {
        this.triggers.push(data[item])
      }
    })
  }

  ngAfterViewInit(): void {
    this.refresh()
  }

  getRules(triggerName) {
    this.properties = []
    this.rules = []

    let sendData = {
      "name": triggerName
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/getbusinessrules', sendData).subscribe(data => {
      for (const item in data["body"]) {
        this.rules.push(data["body"][item])
      }
    })
  }

  getProperties(ruleName) {
    this.properties.push(ruleName)
  }

  runGenerate(triggerName) {

  }

  refresh() {
    setTimeout(() => {
      M.AutoInit()
    }, 10)
  }
}
