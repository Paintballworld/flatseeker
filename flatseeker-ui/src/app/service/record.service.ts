import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { RecordRow } from "../model/RecordRow";
import { Observable } from "rxjs";
import { ApartmentRecord } from "../model/ApartmentRecord";
import { RecordEvent } from "../model/RecordEvent";
import { DuplicateRow } from "../model/DuplicateRow";

@Injectable({providedIn: 'root'})
export class RecordService {

  constructor(private http: HttpClient) {
  }

  getRecordRows(): Observable<RecordRow[]> {
    return this.http.get<RecordRow[]>(`${environment.apiUrl}/records/`);
  }

  getRecords(): Observable<ApartmentRecord[]> {
    return this.http.get<ApartmentRecord[]>(`${environment.apiUrl}/records/`);
  }

  getRecord(id: string): Observable<ApartmentRecord> {
    return this.http.get<ApartmentRecord>(`${environment.apiUrl}/records/${id}`);
  }

  updateRecord(record: ApartmentRecord): Observable<any> {
    return this.http.put(`${environment.apiUrl}/records/`, record);
  }

  updateProcessStatus(id: string, newStatus: string): Observable<ApartmentRecord> {
    let updateStatusRequest = {"id": id, "newStatus": newStatus};
    return this.http.put<ApartmentRecord>(`${environment.apiUrl}/records/update-status`, updateStatusRequest);
  }

  getEvents(id: string): Observable<RecordEvent[]> {
    return this.http.get<RecordEvent[]>(`${environment.apiUrl}/records/events/${id}`);
  }

  submitComment(id: string, comment: string): Observable<any> {
    let submitComment = {"id": id, "comment": comment};
    return this.http.post<RecordEvent[]>(`${environment.apiUrl}/records/events/`, submitComment);
  }

  loadDuplicates(id: string): Observable<DuplicateRow[]> {
    return this.http.get<DuplicateRow[]>(`${environment.apiUrl}/records/duplicates/${id}`);
  }

}
