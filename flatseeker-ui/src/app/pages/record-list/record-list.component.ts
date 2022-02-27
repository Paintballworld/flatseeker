import { Component, OnInit } from '@angular/core';
import { RecordService } from "../../service/record.service";
import { RecordRow } from "../../model/RecordRow";

@Component({
  selector: 'app-record-list',
  templateUrl: './record-list.component.html',
  styleUrls: ['./record-list.component.css']
})
export class RecordListComponent implements OnInit {

  page: number = 1;
  records: RecordRow[] = [];
  loading: boolean = false;

  constructor(
    private recordService: RecordService
  ) {
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.loading = true;
    this.recordService.getRecords()
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

}
