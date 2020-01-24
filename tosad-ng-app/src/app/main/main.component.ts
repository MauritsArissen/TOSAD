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
  values = [];
  failureMessageText: string = "";
  query: string = "";
  listLength: number = 6;
  selectedAttribute: string = "";
  inputType: string = "";
  data: Object = {}
  tables: string[] = [];
  attributes: string[] = [];
  categories: string[] = [];
  businessRuleTypes: string[] = [];
  operators: string[] = [];

  ngOnInit() {
  }  

  ngAfterViewInit() {
    this._http.getRequest("http://localhost:8080/tosad-api/restservices/define/").subscribe(rdata => {
      this.data = rdata
      this.tables = Object.keys(this.data['datatable']);
      this.refresh()
    })
    this.refresh()
  }
 
  sendDefine() {
    let sendData = {
      "table": this.table,
      "attribute": this.attribute,
      "category": this.category,
      "ruletype": this.ruletype,
      "operator": this.operator,
      "values": this.values,
      "failureMessage": this.failureMessageText,
      "triggerEvent": "BEFORE DELETE OR INSERT OR UPDATE"
    }
    this._http.postRequest('http://localhost:8080/tosad-api/restservices/define/saverule', sendData).subscribe(o => {
      console.log("Post request sent")
    })
  }

  tableChange() {
    this.attributes = Object.keys(this.data['datatable'][this.table]);
    this.attribute = ""
    this.attributeChange()
    this.refresh()
  }

  attributeChange() {
    this.categories = Object.keys(this.data['categories']); 
    this.category = ""
    this.businessRuleTypes = []
    this.ruletype = ""
    this.operator = ""
    this.operators = []
    this.refresh()
  }

  categoryChange() {
    this.businessRuleTypes = Object.keys(this.data['categories'][this.category]);
    this.ruletype = ""
    this.operator = ""
    this.operators = []
    this.refresh()
  }

  businessRuleTypeChange() {
    this.operators = this.data['categories'][this.category][this.ruletype].operators
    this.operator = ""
    this.values = []
    this.inputType = this.data['datatable'][this.table][this.attribute]['type']; 
    this.refresh()
  } 

  refresh() {
    setTimeout(() => {
      M.AutoInit()
    }, 10)
  }

  arrayOne(): any[] {
    return [...Array(this.listLength).keys()]
  }
  
  addField() {
    if (this.listLength == 12) return;
    this.listLength += 1
  }

  removeField() {
    if (this.listLength == 2) return;
    this.listLength -= 1
    this.values.splice(-1, 1)
  }

}
