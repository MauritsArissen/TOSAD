import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpService } from '../http.service';
import { DataService } from '../data.service';

declare const M;

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.scss']
})
export class ManageComponent implements OnInit, AfterViewInit {

  constructor(private _http: HttpService, private _data: DataService) { }

  triggers = []
  rules = []
  properties = []
  triggerCode = []
  triggerName: String = null
  selectedIndexTriggers: number = null
  selectedIndexRules: number = null
  selectedRule: String = ''
  data: Object = {}
  table: string = ''
  attribute: string = ''
  category: string = ''
  ruletype: string = ''
  operator: string = ''
  values: string[] = []
  failureMessageText: string = ''

  ngOnInit() {
    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate', this._data.getCredentials()).subscribe(data => {
      if (!data['body']) return
      for (const item in data['body']) {
        this.triggers.push(data['body'][item])
      }
    })
  }

  ngAfterViewInit(): void {
    this.refresh()
  }

  setIndexTriggers(index: number) {
    this.selectedIndexTriggers = index
  }

  setIndexRules(index: number) {
    this.selectedIndexRules = index
  }

  getRules(triggerName) {
    this.properties = []
    this.rules = []
    this.selectedIndexRules = null

    let sendData = {
      'name': triggerName
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/getbusinessrules', sendData).subscribe(data => {
      if (!data['body']) return
      for (const item in data['body']) {
        this.rules.push(data['body'][item])
      }
    })
  }

  getProperties(ruleName) {
    this.properties = []
    this.properties.push(ruleName)
  }

  runGenerate(triggerName) {
    this.triggerCode = []
    this.triggerName = triggerName

    let sendData = {
      'name': triggerName,
      'credentials': this._data.getCredentials()
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/generateTriggerCode', sendData).subscribe(data => {
      if (!data['body']) return
      this.triggerCode = data['body']
    })
    
  }

  // UNFINISHED, retrieve the selected definition from define database
  runEdit(item) {
    this.selectedRule = item
    let sendData = {
      'name': this.selectedRule,
      'credentials': this._data.getCredentials()
    }
    this._http.postRequest('http://localhost:8080/tosad-api/restservices/define/definition/', sendData).subscribe(ddata => {
      this.data = ddata

      this.refresh()
    })
  }

  runCode() {
    let sendData = {
      'name': this.triggerName,
      'credentials': this._data.getCredentials()
    }

    this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/generateTrigger', sendData).subscribe(data => {
      if (!data['body']) return
      for (const item in data['body']) {
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
      'name': this.selectedRule,
      'credentials': this._data.getCredentials()
    }
    let deleteData = {
      'name': this.triggers[this.selectedIndexTriggers],
      'credentials': this._data.getCredentials()
    }
    
    this._http.postRequest('http://localhost:8080/tosad-api/restservices/define/deleterule', sendData).subscribe(data => {
      if (!data["body"]) return;
      var newList = this.rules.slice()
      this.rules = []
      for (const it of newList) {
        if (it == this.selectedRule) continue
        this.rules.push(it)
      }
      this._http.postRequest('http://localhost:8080/tosad-api/restservices/generate/deleteTrigger', deleteData).subscribe(data => {});
    })
  }

}

