import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { NewpasswordComponent } from './components/newpassword/newpassword.component';
import { AppMaterialModule } from './app-material.module';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './services/http.service';
import { UserService } from './services/user.service';
import { NoteService } from './services/note.service';
import { ChangepasswordComponent } from './components/changepassword/changepassword.component';
import { NoteComponent } from './components/note/note.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ArchiveComponent } from './components/archive/archive.component';
import { TrashComponent } from './components/trash/trash.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DialogComponent } from './components/dialog/dialog.component';
import { MatDialogModule } from "@angular/material";
import { ColorPickerModule } from 'ngx-color-picker';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { ReminderComponent } from './components/reminder/reminder.component';
import { MatChipsModule } from '@angular/material/chips';
import { TemplateComponent } from './components/template/template.component';
import {MatDividerModule} from '@angular/material/divider';
import { Angular5TimePickerModule } from 'angular5-time-picker';
import { LabelComponent } from './components/label/label.component';
import { CreateNoteTemplateComponent } from './components/create-note-template/create-note-template.component';
import { MyFilter } from './filter/pin-filter';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NewpasswordComponent,
    ChangepasswordComponent,
    NoteComponent,
    ArchiveComponent,
    TrashComponent,
    DialogComponent,
    ReminderComponent,
    TemplateComponent,
    LabelComponent,
    CreateNoteTemplateComponent,
    MyFilter
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppMaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule,
    MatTooltipModule,
    MatDialogModule,
    ColorPickerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatChipsModule,
    MatDividerModule,
    Angular5TimePickerModule
  ],
  entryComponents: [DialogComponent],
  providers: [HttpService, UserService, NoteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
