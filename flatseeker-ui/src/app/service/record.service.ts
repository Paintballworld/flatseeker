import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { RecordRow } from "../model/RecordRow";
import { Observable } from "rxjs";

@Injectable({providedIn: 'root'})
export class RecordService {

  constructor(private http: HttpClient) {
  }

  getRecords(): Observable<RecordRow[]> {
    return this.http.get<RecordRow[]>(`${environment.apiUrl}/records/`);
  }

}
