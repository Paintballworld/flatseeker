import { ApartmentRecord } from "./ApartmentRecord";

export class LinkResolveResponse {
  public record: ApartmentRecord;
  public alreadyExists: boolean;

  constructor(record: ApartmentRecord, alreadyExists: boolean) {
    this.record = record;
    this.alreadyExists = alreadyExists;
  }
}
