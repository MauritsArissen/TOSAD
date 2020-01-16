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

  /////////////////////////////////////////// EXAMPLE DATA
  data = {
    categories: {
      "Static data constraint rules": {
        rangerule: {
          operators: ["between", "not between"]
        },
        comparerule: {
          operators: ["=", ">=", "<=", "<", ">"]
        },
      },
      "Dynamic data constraint rules": {}
    },
    table: {
      lessons: ["name", "length", "teacher"],
      food: ["name", "calories"]
    }
  }

  tables = Object.keys(this.data.table);
  attributes = [];
  categories = Object.keys(this.data.categories);
  businessRuleTypes = [];
  operators = [];
  ////////////////////////////////////////////////////////

  ngOnInit() {
    this._http.getRequest("http://localhost:8080/tosad-api/restservices/define/").subscribe(data => {
      console.log(data);
    })
  }  

  ngAfterViewInit() {
    this.refresh()
  }

  tableChange() {
    this.attributes = this.data.table[this.table]
    this.attribute = ""
    setTimeout(() => {
      this.refresh()
    }, 10)
  }

  categoryChange() {
    this.businessRuleTypes = Object.keys(this.data.categories[this.category]);
    this.ruletype = ""
    this.operator = ""
    this.operators = []
    setTimeout(() => {
      this.refresh()
    }, 10)
  }

  businessRuleTypeChange() {
    this.operators = this.data.categories[this.category][this.ruletype].operators
    this.operator = ""
    setTimeout(() => {
      this.refresh()
    }, 10)
  } 

  refresh() {
    M.AutoInit()
  }

}
