import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare var ipcRenderer: any;

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  public define: boolean = true;

  constructor(private router: Router) {}

  ngOnInit() {
  }

  minApp() {
    ipcRenderer.send("min-app")
  }

  quitApp() {
    ipcRenderer.send("close-app")
  }

  setDefine(bool: boolean) {
    this.define = bool;
  }

  checkPage(): boolean {
    return this.router.url != "/"
  }

}
