import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';

declare const M;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit {

  dbTypes: string[] = ["Oracle"];
  dbType: string = "Oracle";
  host: string = "ondora04.hu.nl";
  port: number = 1521;
  SID: string = "";
  ServiceName: string = "EDUC10";
  username: string = "maurits";
  password: string = "maurits";

  constructor(private router: Router) { }

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

  connect() {
    if (this.host.length == 0) {
      return M.toast({html: "A host is required to connect", classes: "errorToast"})
    } else if (this.port == (null || undefined || 0) || this.port < 1 || this.port > 65565) {
      return M.toast({html: "You must choose a port from 1-65565", classes: "errorToast"})
    } else if (this.SID == "" && this.ServiceName == "") {
      return M.toast({html: "You must fill in either SID or Service name", classes: "errorToast"})
    } else if (this.username == "") {
      return M.toast({html: "You must fill in a username", classes: "errorToast"})
    } else if (this.password == "") {
      return M.toast({html: "You must fill in a password", classes: "errorToast"})
    } else if (this.dbType == "") {
      return M.toast({html: "You must select one of the supported database types", classes: "errorToast"})
    }
    this.router.navigate(['/define'])
  }

}
