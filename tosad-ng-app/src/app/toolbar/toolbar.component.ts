import { Component, OnInit, NgZone } from '@angular/core';

declare var ipcRenderer: any;
declare var remote: any;

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  private ipc_info: string;
  private remote_info: string;

  constructor(zone: NgZone) {}

  ngOnInit() {
  }

  minApp() {
    ipcRenderer.send("min-app")
  }

  quitApp() {
    ipcRenderer.send("close-app")
  }

}
