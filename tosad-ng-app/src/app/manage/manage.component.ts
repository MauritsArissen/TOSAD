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
  triggerCode = []
  triggerName: String = null
  selectedIndexTriggers: number = null
  selectedIndexRules: number = null
  selectedRule: String = "";

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

  setIndexTriggers(index: number) {
    this.selectedIndexTriggers = index;
  }

  setIndexRules(index: number) {
    this.selectedIndexRules = index;
  }

  getRules(triggerName) {
    this.properties = []
    this.rules = []
    this.selectedIndexRules = null;

    let sendData = {
      'name': triggerName
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/getbusinessrules', sendData).subscribe(data => {
      for (const item in data['body']) {
        this.rules.push(data['body'][item])
      }
    })
  }

  getProperties(ruleName) {
    this.properties.push(ruleName)
  }

  runGenerate(triggerName) {
    this.triggerCode = []
    this.triggerName = triggerName

    let sendData = {
      'name': triggerName
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/generateTriggerCode', sendData).subscribe(data => {
      this.triggerCode = data['body']
    })
    
  }

  // Edit to retrieve definition
  runEdit(item) {
    this.selectedRule = item

 
    let sendData = {
      'name': this.selectedRule
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/define/deleterule', sendData).subscribe(data => {
      var newList = this.rules.slice();
      this.rules = [];
      for (const it of newList) {
        if (it == this.selectedRule) continue;
        this.rules.push(it)
      }
    })
  }

  runCode() {
    let sendData = {
      'name': this.triggerName
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/generateTrigger', sendData).subscribe(data => {
      for (const item in data["body"]) {
        M.toast({html: data['body'][item]})
      }
    })
  }

  refresh() {
    setTimeout(() => {
      M.AutoInit()
    }, 10)
  }

  deleteModal(item) {
    this.selectedRule = item
  }

  deleteRule() {    
    let sendData = {
      'name': this.selectedRule
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/define/deleterule', sendData).subscribe(data => {
      var newList = this.rules.slice();
      this.rules = [];
      for (const it of newList) {
        if (it == this.selectedRule) continue;
        this.rules.push(it)
      }
    })
  }

}

