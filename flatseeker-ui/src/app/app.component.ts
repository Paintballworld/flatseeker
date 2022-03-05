import { Component } from '@angular/core';
import { AuthenticationService } from "./service/authentication.service";
import { Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { ApartmentRecord } from "./model/ApartmentRecord";
import { RecordService } from "./service/record.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'flatseeker-ui';
  linkResolveContent: string = "";
  showModal: boolean = false;
  resolvedRecord: ApartmentRecord = ApartmentRecord.mock();

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private messageService: NzMessageService,
    private recordService: RecordService
  ) {
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login'])
      .then(r => console.log("navigate -> success"));
  }

  resolveLink(): void {
    this.recordService.resolveLink(this.linkResolveContent)
      .subscribe({
        next: data => {
          this.linkResolveContent = "";
          this.resolvedRecord = data;
          this.showModal = true;
        },
        error: message => {
          this.messageService.error("Невозможно получить данные" + message);
          this.linkResolveContent = "";
          this.showModal = true;
        }
      })
  }

  cancelLink(): void {
    this.linkResolveContent = "";
    this.resolvedRecord = ApartmentRecord.mock();
    this.showModal = false;
  }

  saveLink(): void {
    this.recordService.save(this.resolvedRecord)
      .subscribe({
        next: ignore => {
          this.showModal = false;
          this.linkResolveContent = "";
          this.resolvedRecord = ApartmentRecord.mock();
        },
        error: message => {
          this.showModal = false;
          this.linkResolveContent = "";
          this.resolvedRecord = ApartmentRecord.mock();
          this.messageService.error("Не удалось сохранить объявление: " + message);
        }
      })

  }
}
