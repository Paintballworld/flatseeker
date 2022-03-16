import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";
import { ProcessStatus } from "../model/dict/ProcessStatus";
import { LocationStatus } from "../model/dict/LocationStatus";
import { BathroomStatus } from "../model/dict/BathroomStatus";
import { ApartmentType } from "../model/dict/ApartmentType";
import { AnimalStatus } from "../model/dict/AnimalStatus";

@Injectable({providedIn: 'root'})
export class DictService {

  protected animalStatuses: AnimalStatus[] = [];
  protected apartmentTypes: ApartmentType[] = [];
  protected bathroomStatuses: BathroomStatus[] = [];
  protected locationStatuses: LocationStatus[] = [];
  protected processStatuses: ProcessStatus[] = [];

  constructor(private http: HttpClient) {
    this.loadAnimalStatuses()
      .subscribe({
        next: list => this.animalStatuses = list
      });
    this.loadApartmentTypes()
      .subscribe({
        next: list => this.apartmentTypes = list
      });
    this.loadBathroomStatuses()
      .subscribe({
        next: list => this.bathroomStatuses = list
      });
    this.loadLocationStatuses()
      .subscribe({
        next: list => this.locationStatuses = list
      });
    this.loadProcessStatuses()
      .subscribe({
        next: list => this.processStatuses = list
      });
  }

  loadAnimalStatuses(): Observable<AnimalStatus[]> {
    return this.http.get<AnimalStatus[]>(`${environment.apiUrl}/dict/AnimalStatus`);
  }

  loadApartmentTypes(): Observable<ApartmentType[]> {
    return this.http.get<ApartmentType[]>(`${environment.apiUrl}/dict/ApartmentType`);
  }

  loadBathroomStatuses(): Observable<BathroomStatus[]> {
    return this.http.get<BathroomStatus[]>(`${environment.apiUrl}/dict/BathroomStatus`);
  }

  loadLocationStatuses(): Observable<LocationStatus[]> {
    return this.http.get<LocationStatus[]>(`${environment.apiUrl}/dict/LocationStatus`);
  }

  loadProcessStatuses(): Observable<ProcessStatus[]> {
    return this.http.get<ProcessStatus[]>(`${environment.apiUrl}/dict/ProcessStatus`);
  }

}
