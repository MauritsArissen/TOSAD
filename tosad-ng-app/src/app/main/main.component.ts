import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpService } from '../http.service';

declare const M;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, AfterViewInit {

  constructor(private _http: HttpService) { }

  table: string = "";
  attribute: string = "";
  category: string = "";
  ruletype: string = "";
  operator: string = "";
  firstParam: number;
  secondParam: number;
  failureMessageText: string = "";
  query: string = "";

  /////////////////////////////////////////// EXAMPLE DATA
  data = {}
  datatable = {
    lessons: ["name", "length", "teacher"],
    food: ["name", "calories"]
  }
  tables = [];
  attributes = [];
  categories = [];
  businessRuleTypes = [];
  operators = [];
  ////////////////////////////////////////////////////////

  ngOnInit() {
  }  

  ngAfterViewInit() {
    this._http.getRequest("http://localhost:8080/tosad-api/restservices/define/").subscribe(rdata => {
      this.data = rdata
      this.tables = Object.keys(this.datatable);
      this.categories = Object.keys(this.data['categories']); 
      setTimeout(() => {
        this.refresh()
      }, 10)
    })
  }

  sendDefine() {
    let sendData = {
      "table": this.table,
      "attribute": this.attribute,
      "category": this.category,
      "ruletype": this.ruletype,
      "operator": this.operator,
      "firstParam": this.firstParam,
      "secondParam": this.secondParam,
      "failureMessage": this.failureMessageText
    }
    // this._http.postRequest("http://localhost:8080/tosad-api/restservices/define/savedefined", sendData, null).subscribe(data => {
    //   console.log(data);
    // })
    this.generateCode(sendData)
  }

  tableChange() {
    this.attributes = this.datatable[this.table]
    this.attribute = ""
    setTimeout(() => {
      this.refresh()
    }, 10)
  }

  categoryChange() {
    this.businessRuleTypes = Object.keys(this.data['categories'][this.category]);
    this.ruletype = ""
    this.operator = ""
    this.operators = []
    setTimeout(() => {
      this.refresh()
    }, 10)
  }

  businessRuleTypeChange() {
    this.operators = this.data['categories'][this.category][this.ruletype].operators
    this.operator = ""
    setTimeout(() => {
      this.refresh()
    }, 10)
  } 

  refresh() {
    M.AutoInit()
  }

  generateCode(data) {
    this.query = `CREATE OR REPLACE TRIGGER generated_name BEFORE INSERT ON ${data.table} FOR EACH ROW DECLARE l_passed boolean; BEGIN l_passed := ${data.attribute} ${data.operator} ${data.firstParam} AND ${data.secondParam}; IF NOT l_passed THEN raise_application_error(-20000, '${data.failureMessage}'); END IF; END generated_name;`
  }

}
