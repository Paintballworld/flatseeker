export class ProcessStatusDict {
  private _key: string;
  private _title: string;
  private _color: string;

  constructor(key: string, title: string, color: string) {
    this._key = key;
    this._title = title;
    this._color = color;
  }

  get key(): string {
    return this._key;
  }

  get title(): string {
    return this._title;
  }

  get color(): string {
    return this._color;
  }
}
