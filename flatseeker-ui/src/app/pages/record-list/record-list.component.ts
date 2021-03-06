import { Component, OnDestroy, OnInit } from '@angular/core';
import { RecordService } from "../../service/record.service";
import { RecordRow } from "../../model/RecordRow";
import { ApartmentRecord } from "../../model/ApartmentRecord";
import { NzMessageService } from 'ng-zorro-antd/message';
import { DictService } from "../../service/dict.service";
import { AnimalStatus } from "../../model/dict/AnimalStatus";
import { ApartmentType } from "../../model/dict/ApartmentType";
import { BathroomStatus } from "../../model/dict/BathroomStatus";
import { LocationStatus } from "../../model/dict/LocationStatus";
import { ProcessStatus } from "../../model/dict/ProcessStatus";
import { RecordEvent } from "../../model/RecordEvent";
import { DuplicateRow } from "../../model/DuplicateRow";
import { NewRecordService } from "../../service/new-record.service";
import { Subscription } from "rxjs";

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.css']
})
export class RecordListComponent implements OnInit, OnDestroy {

  page: number = 1;
  records: RecordRow[] = [];
  loading: boolean = false;
  selectedRecord: ApartmentRecord = ApartmentRecord.mock();
  drawerOpen: boolean = false;

  formatterZloty = (value: number): string => `${value ? value : 0} zł`;
  parserZloty = (value: string): string => value.replace(' zł', '');

  animalStatuses: AnimalStatus[] = [];
  apartmentTypes: ApartmentType[] = [];
  bathroomStatuses: BathroomStatus[] = [];
  locationStatuses: LocationStatus[] = [];
  processStatuses: ProcessStatus[] = [];

  selectedRecordEvents: RecordEvent[] = [];
  duplicateList: DuplicateRow[] = [];

  newCommentField: string = "";
  // @ts-ignore
  newRecordSubscription: Subscription;

  constructor(
    private recordService: RecordService,
    private message: NzMessageService,
    private dictService: DictService,
    private newRecordService: NewRecordService
  ) {
  }

  ngOnInit(): void {
    this.refresh();
    this.dictService.loadAnimalStatuses()
      .subscribe({
        next: list => this.animalStatuses = list
      });
    this.dictService.loadApartmentTypes()
      .subscribe({
        next: list => this.apartmentTypes = list
      });
    this.dictService.loadBathroomStatuses()
      .subscribe({
        next: list => this.bathroomStatuses = list
      });
    this.dictService.loadLocationStatuses()
      .subscribe({
        next: list => this.locationStatuses = list
      });
    this.dictService.loadProcessStatuses()
      .subscribe({
        next: list => this.processStatuses = list
      });
    this.newRecordSubscription = this.newRecordService.eventEmitter
      .subscribe(record => this.records.unshift(RecordRow.of(record)));
  }

  log(e: any) {
    console.log(e)
  }

  refresh(): void {
    this.loading = true;
    this.recordService.getRecordRows()
      .subscribe({
        next: data => {
          this.records = data;
          this.loading = false;
        },
        error: message => {
          this.message.error("Error on load list", message);
          this.loading = false;
          this.records = [];
        }
      });
  }


  updateStatus(itemId: string): void {
    console.log("Trying to update status of:", itemId);
  }

  editRecord(recordRow: RecordRow): void {
    recordRow.viewed = true;
    this.recordService.getRecord(recordRow.id)
      .subscribe({
        next: data => {
          this.selectedRecord = data;
          this.drawerOpen = true;
        },
        error: message => this.handleError(message)
      });
    this.recordService.getEvents(recordRow.id)
      .subscribe({
        next: list => this.selectedRecordEvents = list,
        error: message => console.log("Error on event update:", message)
      })
    this.recordService.loadDuplicates(recordRow.id)
      .subscribe({
        next: list => this.duplicateList = list,
        error: message => this.handleError(message)
      })
  }

  private handleError(message: any) {
    this.closeDrawer();
    this.message.error("Ошибка загрузки: " + message);
  }

  closeDrawer(): void {
    this.drawerOpen = false;
    this.selectedRecordEvents = [];
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

  submitComment(): void {
    console.log("Submitting comment: ", "<" + this.newCommentField + ">");
    this.recordService.submitComment(this.selectedRecord.id, this.newCommentField)
      .subscribe({
        next: list => {
          this.newCommentField = "";
          this.selectedRecordEvents = list;
        }
      });
  }

  updateProcessStatus(record: RecordRow, newStatus: ProcessStatus): void {
    console.log("Updating status", record.id, newStatus.key);
    this.recordService.updateProcessStatus(record.id, newStatus.key)
      .subscribe({
        next: ignore => record.processStatus = newStatus
      })
  }

  stringify(enumValue: ProcessStatus): string {
    return JSON.stringify(enumValue);
  }

  areEqual(enumValue: ProcessStatus, enumIterable: any): boolean {
    return JSON.stringify(enumValue) === JSON.stringify(enumIterable);
  }

  getStatusDictValue(status: ProcessStatus): boolean {
    let active = this.processStatuses
      .filter(dict => this.areEqual(status, dict.key))
      .map(dict => dict.active)
      .pop();
    return active != undefined ? active : false;
  }

  ngOnDestroy(): void {
    this.newRecordSubscription.unsubscribe();
  }

  remove(id: string): void {
    this.recordService.remove(id)
      .subscribe({
        next: ignore => {
          this.message.info("Запись удалена");
          this.refresh()
        },
        error: message => this.message.error("Не возможно удалить запись <br/>" + message)
      });
  }
}
