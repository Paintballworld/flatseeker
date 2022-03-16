export class AnimalStatus {
  public key: string;
  public title: string;

  constructor(key: string, title: string) {
    this.key = key;
    this.title = title;
  }

  public static mock() {
    return new AnimalStatus("", "");
  }
}
