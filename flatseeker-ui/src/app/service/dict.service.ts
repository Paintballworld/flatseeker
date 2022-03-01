import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";
import { ProcessStatusDict } from "../model/dict/ProcessStatusDict";
import { LocationStatusDict } from "../model/dict/LocationStatusDict";
import { BathroomStatusDict } from "../model/dict/BathroomStatusDict";
import { ApartmentTypeDict } from "../model/dict/ApartmentTypeDict";
import { AnimalStatusDict } from "../model/dict/AnimalStatusDict";

@Injectable({providedIn: 'root'})
export class DictService {

  protected animalStatuses: AnimalStatusDict[] = [];
  protected apartmentTypes: ApartmentTypeDict[] = [];
  protected bathroomStatuses: BathroomStatusDict[] = [];
  protected locationStatuses: LocationStatusDict[] = [];
  protected processStatuses: ProcessStatusDict[] = [];

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

  loadAnimalStatuses(): Observable<AnimalStatusDict[]> {
    return this.http.get<AnimalStatusDict[]>(`${environment.apiUrl}/dict/AnimalStatus`);
  }

  loadApartmentTypes(): Observable<ApartmentTypeDict[]> {
    return this.http.get<ApartmentTypeDict[]>(`${environment.apiUrl}/dict/ApartmentType`);
  }

  loadBathroomStatuses(): Observable<BathroomStatusDict[]> {
    return this.http.get<BathroomStatusDict[]>(`${environment.apiUrl}/dict/BathroomStatus`);
  }

  loadLocationStatuses(): Observable<LocationStatusDict[]> {
    return this.http.get<LocationStatusDict[]>(`${environment.apiUrl}/dict/LocationStatus`);
  }

  loadProcessStatuses(): Observable<ProcessStatusDict[]> {
    return this.http.get<ProcessStatusDict[]>(`${environment.apiUrl}/dict/ProcessStatus`);
  }

}
