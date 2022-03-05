import { Component, EventEmitter, Injectable, OnInit } from '@angular/core';
import { ApartmentRecord } from "../model/ApartmentRecord";

@Injectable({providedIn: 'root'})
export class NewRecordService implements OnInit {

  public eventEmitter: EventEmitter<ApartmentRecord> = new EventEmitter<ApartmentRecord>();

  constructor() {
  }

  ngOnInit() {
  }

  publishNewRecord(record: ApartmentRecord): void {
    this.eventEmitter.emit(record);
  }

}
