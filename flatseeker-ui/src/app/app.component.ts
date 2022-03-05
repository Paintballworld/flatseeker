import { Component, EventEmitter } from '@angular/core';
import { AuthenticationService } from "./service/authentication.service";
import { Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { ApartmentRecord } from "./model/ApartmentRecord";
import { RecordService } from "./service/record.service";
import { NewRecordService } from "./service/new-record.service";
import { LinkResolveResponse } from "./model/LinkResolveResponse";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'flatseeker-ui';
  linkResolveContent: string = "";
  showModal: boolean = false;
  resolveResponse: LinkResolveResponse = new LinkResolveResponse(ApartmentRecord.mock(), false);
  requestProcessing: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private messageService: NzMessageService,
    private recordService: RecordService,
    private newRecordService: NewRecordService
  ) {
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login'])
      .then(r => console.log("navigate -> success"));
  }

  resolveLink(): void {
    this.requestProcessing = true;
    this.recordService.resolveLink(this.linkResolveContent)
      .subscribe({
        next: data => {
          this.linkResolveContent = "";
          this.resolveResponse = data;
          this.showModal = true;
          this.requestProcessing = false;
        },
        error: message => {
          this.messageService.error("Невозможно получить данные" + message);
          this.linkResolveContent = "";
          this.showModal = true;
          this.requestProcessing = false;
        }
      })
  }

  cancelLink(): void {
    this.linkResolveContent = "";
    this.resolveResponse.record = ApartmentRecord.mock();
    this.resolveResponse.alreadyExists = false;
    this.showModal = false;
  }

  saveLink(): void {
    this.recordService.save(this.resolveResponse.record)
      .subscribe({
        next: saved => {
          this.showModal = false;
          this.linkResolveContent = "";
          this.resolveResponse.record = ApartmentRecord.mock();
          this.resolveResponse.alreadyExists = false;
          this.newRecordService.publishNewRecord(saved);
        },
        error: message => {
          this.showModal = false;
          this.linkResolveContent = "";
          this.resolveResponse.record = ApartmentRecord.mock();
          this.resolveResponse.alreadyExists = false;
          this.messageService.error("Не удалось сохранить объявление: " + message);
        }
      });
  }
}
