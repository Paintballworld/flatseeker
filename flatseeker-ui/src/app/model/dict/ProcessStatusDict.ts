export class ProcessStatusDict {
  public key: string;
  public title: string;
  public color: string;
  public active: boolean;

  constructor(key: string, title: string, color: string, active: boolean) {
    this.key = key;
    this.title = title;
    this.color = color;
    this.active = active;
  }
}
