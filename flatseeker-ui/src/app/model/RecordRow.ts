import { ProcessStatus } from "./ProcessStatus";

export class RecordRow {
  public id: string;
  public title: string;
  public rentPrice: number;
  public feePrice: number;
  public platformName: string;
  public description: string;
  public mainImageUrl: string;
  public area: number;
  public processStatus: ProcessStatus;
  public createdAt: Date;
  public link: string;
  public totalPrice: number;

  constructor(id: string, title: string, rentPrice: number, feePrice: number, platformName: string, description: string, mainImageUrl: string, area: number, status: ProcessStatus, createdAt: Date, link: string, totalPrice: number) {
    this.id = id;
    this.title = title;
    this.rentPrice = rentPrice;
    this.feePrice = feePrice;
    this.platformName = platformName;
    this.description = description;
    this.mainImageUrl = mainImageUrl;
    this.area = area;
    this.processStatus = status;
    this.createdAt = createdAt;
    this.link = link;
    this.totalPrice = totalPrice;
  }
}
