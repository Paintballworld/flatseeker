export class Record {
  public id: string;
  public title: string;
  public calories: number;
  public price: number;
  public date: Date;

  constructor(id: string, title: string, calories: number, price: number, date: Date) {
    this.id = id;
    this.title = title;
    this.calories = calories;
    this.price = price;
    this.date = date;
  }
}
