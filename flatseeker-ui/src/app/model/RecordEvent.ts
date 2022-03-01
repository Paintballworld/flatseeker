export class RecordEvent {
  public id: string;
  public type: string;
  public createdAt: Date;
  public details: string;

  constructor(id: string, type: string, createdAt: Date, details: string) {
    this.id = id;
    this.type = type;
    this.createdAt = createdAt;
    this.details = details;
  }
}
