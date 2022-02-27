import { ProcessStatus } from "./processStatus";

export class RecordRow {
  public id: string;
  public title: string;
  public totalPrice: number;
  public area: number;
  public status: ProcessStatus

  constructor(id: string, title: string, totalPrice: number, area: number, status: ProcessStatus) {
    this.id = id;
    this.title = title;
    this.totalPrice = totalPrice;
    this.area = area;
    this.status = status;
  }
}
