export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string;
  token?: string;

  constructor(id: number, username: string, password: string, firstName: string, lastName: string, role: string, token: string) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.token = token;
  }
}
