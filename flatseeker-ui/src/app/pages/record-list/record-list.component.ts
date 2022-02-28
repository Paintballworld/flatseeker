import { Component, OnInit } from '@angular/core';
import { RecordService } from "../../service/record.service";
import { RecordRow } from "../../model/RecordRow";
import { ApartmentRecord } from "../../model/ApartmentRecord";
import { NzMessageService } from 'ng-zorro-antd/message';
import { AnimalsStatus } from "../../model/AnimalsStatus";
import { FormBuilder, FormGroup } from "@angular/forms";
import { BathroomStatus } from "../../model/BathroomStatus";

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.css']
})
export class RecordListComponent implements OnInit {

  page: number = 1;
  records: RecordRow[] = [];
  loading: boolean = false;
  // @ts-ignore
  selectedRecord: ApartmentRecord = new ApartmentRecord();
  drawerOpen: boolean = false;
  AnimalsStatus = AnimalsStatus;
  BathroomStatus = BathroomStatus;

  validateForm!: FormGroup;

  constructor(
    private recordService: RecordService,
    private message: NzMessageService
  ) {
  }

  ngOnInit(): void {
    this.refresh();
  }

  log(e: any) {
    console.log(e)
  }

  refresh(): void {
    this.loading = true;
    this.recordService.getRecordRows()
      .subscribe(
        data => {
          this.records = data;
          this.loading = false;
        },
        error => [
          console.log("Error", error)
        ]
      );
  }

  updateStatus(itemId: string): void {
    console.log("Trying to update status of:", itemId);
  }

  editRecord(id: string): void {
    this.recordService.getRecord(id)
      .subscribe({
        next: data => {
          console.log("Record loaded:", data);
          this.selectedRecord = data;
          this.drawerOpen = true;
        },
        error: message => this.handleError(message)
      });
  }

  private handleError(message: any) {
    this.closeDrawer();
    this.message.error("Ошибка загрузки Объявления", message);
  }

  closeDrawer(): void {
    this.drawerOpen = false;
  }

  submitForm(): void {
    console.log('submit', this.selectedRecord);
    this.recordService.updateRecord(this.selectedRecord)
      .subscribe({
        next: ignore => {
          this.refresh();
          this.drawerOpen = false;
        }
      })
  }

}
