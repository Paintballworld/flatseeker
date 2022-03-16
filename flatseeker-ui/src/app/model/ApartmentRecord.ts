import { AnimalStatus } from "./dict/AnimalStatus";
import { BathroomStatus } from "./dict/BathroomStatus";
import { ApartmentType } from "./dict/ApartmentType";
import { LocationStatus } from "./dict/LocationStatus";
import { ProcessStatus } from "./dict/ProcessStatus";

export class ApartmentRecord {
  public id: string;
  public title: string;
  public description: string;
  public rentPrice: number;
  public feePrice: number;
  public deposit: number;
  public area: number;
  public conditioner: Boolean;
  public animalsStatus: AnimalStatus;
  public bathroomStatus: BathroomStatus;
  public location: string;
  public apartmentType: ApartmentType;
  public createdAt: Date;
  public mainImageUrl: string;
  public locationStatus: LocationStatus;
  public link: string;
  public platformName: string;
  public processStatus: ProcessStatus;
  public viewed: boolean;

  constructor(id: string, title: string, description: string, rentPrice: number, feePrice: number, deposit: number, area: number, conditioner: Boolean, animalStatus: AnimalStatus, bathroomStatus: BathroomStatus, location: string, apartmentType: ApartmentType, createdAt: Date, mainImageUrl: string, locationStatus: LocationStatus, link: string, platformName: string, processStatus: ProcessStatus, viewed: boolean) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rentPrice = rentPrice;
    this.feePrice = feePrice;
    this.deposit = deposit;
    this.area = area;
    this.conditioner = conditioner;
    this.animalsStatus = animalStatus;
    this.bathroomStatus = bathroomStatus;
    this.location = location;
    this.apartmentType = apartmentType;
    this.createdAt = createdAt;
    this.mainImageUrl = mainImageUrl;
    this.locationStatus = locationStatus;
    this.link = link;
    this.platformName = platformName;
    this.processStatus = processStatus;
    this.viewed = viewed;
  }

  public static mock() {
    return new ApartmentRecord("", "", "", 0, 0, 0, 0, false,   AnimalStatus.mock(), BathroomStatus.mock(), "", ApartmentType.mock(), new Date(), "", LocationStatus.mock(), "", "", ProcessStatus.mock(), false);
  }
}
