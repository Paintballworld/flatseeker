import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { en_US, NZ_I18N } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/login/login.component';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { AppRoutingModule } from "./app-routing.module";
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzGridModule } from "ng-zorro-antd/grid";
import { NzBadgeModule } from "ng-zorro-antd/badge";
import { NzAvatarModule } from "ng-zorro-antd/avatar";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzButtonModule } from "ng-zorro-antd/button";
import { RecordListComponent } from './pages/record-list/record-list.component';
import { NzListModule } from 'ng-zorro-antd/list';
import { JwtInterceptor } from "./infrastructure/interceptor/jwt.interceptor";
import { ErrorInterceptor } from "./infrastructure/interceptor/error.interceptor";
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzStepsModule } from 'ng-zorro-antd/steps';
import { NzAffixModule } from 'ng-zorro-antd/affix';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzTagModule } from 'ng-zorro-antd/tag';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RecordListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzMenuModule,
    AppRoutingModule,
    NzLayoutModule,
    NzMenuModule,
    NzIconModule,
    NzGridModule,
    NzBadgeModule,
    NzAvatarModule,
    NzButtonModule,
    NzInputModule,
    NzMenuModule,
    NzListModule,
    NzBadgeModule,
    NzToolTipModule,
    NzDrawerModule,
    NzFormModule,
    NzMessageModule,
    NzSelectModule,
    NzInputNumberModule,
    NzStepsModule,
    NzAffixModule,
    NzCollapseModule,
    NzTabsModule,
    NzEmptyModule,
    NzModalModule,
    NzTagModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    {provide: NZ_I18N, useValue: en_US}
  ],
  bootstrap: [AppComponent]
})
// @ts-ignore
export class AppModule {
}
