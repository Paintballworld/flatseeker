import { Component, OnInit } from '@angular/core';
import { RecordService } from "../../service/record.service";
import { RecordRow } from "../../model/RecordRow";
import { ApartmentRecord } from "../../model/ApartmentRecord";
import { NzMessageService } from 'ng-zorro-antd/message';
import { AnimalsStatus } from "../../model/AnimalsStatus";
import { FormGroup } from "@angular/forms";
import { BathroomStatus } from "../../model/BathroomStatus";
import { LocationStatus } from "../../model/LocationStatus";
import { ApartmentType } from "../../model/ApartmentType";
import { ProcessStatus } from '../../model/ProcessStatus';

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
  ApartmentType = ApartmentType;
  LocationStatus = LocationStatus;
  ProcessStatus = ProcessStatus;

  formatterZloty = (value: number): string => `${value} zł`;
  parserZloty = (value: string): string => value.replace(' zł', '');

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

  updateProcessStatus(record: RecordRow, newStatus: string): void {
    console.log("Updating status", record.id, newStatus);
    this.recordService.updateProcessStatus(record.id, newStatus)
      .subscribe({
        next: ignore => this.refresh()
      })
  }

  stringify(enumValue: ProcessStatus) : string {
    return JSON.stringify(enumValue);
  }

  areEqual(enumValue: ProcessStatus, enumIterable: any): boolean {
    return JSON.stringify(enumValue) === JSON.stringify(enumIterable);
  }

}
