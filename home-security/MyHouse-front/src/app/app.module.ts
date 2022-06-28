import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddTenantModal } from './add-tenant-modal/add-tenant-modal.component';
import { AddDeviceModal } from './add-device-modal/add-device-modal.component';
import { ConfirmModal } from './confirm-modal/confirm-modal.component';
import { AddUserModal } from './add-user-modal/add-user-modal.component';
import {ChangeUserRoleModal} from './change-user-role-modal/change-user-role-modal.component'
import { GenerateReportModal } from './generate-report-modal/generate-report-modal.component';
import {ReportModal} from './report-modal/report-modal.component'
import { BoardObjectsComponent } from './board-objects/board-objects.component';
import {AddObjectModal} from './add-object-modal/add-object-modal.component'

import {MaterialModule} from './material-module'



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardUserComponent,
    AddTenantModal,
    AddDeviceModal,
    ConfirmModal,
    AddUserModal,
    ChangeUserRoleModal,
    GenerateReportModal,
    ReportModal,
    BoardObjectsComponent,
    AddObjectModal
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
