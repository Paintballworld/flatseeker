import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./pages/login/login.component";
import { AuthGuard } from "./infrastructure/guard/auth.guard";
import { RecordListComponent } from "./pages/record-list/record-list.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  { path: 'login',  component: LoginComponent },
  { path: 'records', canActivate: [AuthGuard], component: RecordListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
