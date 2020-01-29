import { Component, OnInit, AfterViewInit } from '@angular/core';

declare const M;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit {

  dbTypes: string[] = ["Oracle"];
  dbType: string = "Select database type";

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.refresh();
  }

  refresh() {
    setTimeout(() => {
      M.AutoInit()
    }, 10)
  }

}
