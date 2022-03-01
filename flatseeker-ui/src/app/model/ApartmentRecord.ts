import { ProcessStatus } from "./ProcessStatus";
import { LocationStatus } from "./LocationStatus";
import { BathroomStatus } from "./BathroomStatus";
import { AnimalsStatus } from "./AnimalsStatus";
import { ApartmentType } from "./ApartmentType";

export class ApartmentRecord {
  public id: string;
  public title: string;
  public description: string;
  public rentPrice: number;
  public feePrice: number;
  public deposit: number;
  public area: number;
  public conditioner: Boolean;
  public animalsStatus: AnimalsStatus;
  public bathroomStatus: BathroomStatus;
  public location: string;
  public type: ApartmentType;
  public createdAt: Date;
  public mainImageUrl: string;
  public locationStatus: LocationStatus;
  public link: string;
  public platformName: string;
  public processStatus: ProcessStatus;
  public comment: string;
  public viewed: boolean;

  constructor(id: string, title: string, description: string, rentPrice: number, feePrice: number, deposit: number, area: number, conditioner: Boolean, animalsStatus: AnimalsStatus, bathroomStatus: BathroomStatus, location: string, type: ApartmentType, createdAt: Date, mainImageUrl: string, locationStatus: LocationStatus, link: string, platformName: string, processStatus: ProcessStatus, comment: string, viewed: boolean) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rentPrice = rentPrice;
    this.feePrice = feePrice;
    this.deposit = deposit;
    this.area = area;
    this.conditioner = conditioner;
    this.animalsStatus = animalsStatus;
    this.bathroomStatus = bathroomStatus;
    this.location = location;
    this.type = type;
    this.createdAt = createdAt;
    this.mainImageUrl = mainImageUrl;
    this.locationStatus = locationStatus;
    this.link = link;
    this.platformName = platformName;
    this.processStatus = processStatus;
    this.comment = comment;
    this.viewed = viewed;
  }
}
