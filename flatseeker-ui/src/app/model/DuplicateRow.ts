export class DuplicateRow {
  public id: string;
  public title: string;
  public link: string;
  public platformName: string;
  public createdAt: Date;

  constructor(id: string, title: string, link: string, platformName: string, createdAt: Date) {
    this.id = id;
    this.title = title;
    this.link = link;
    this.platformName = platformName;
    this.createdAt = createdAt;
  }
}
