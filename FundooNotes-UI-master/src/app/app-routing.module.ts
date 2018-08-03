import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NewpasswordComponent } from './components/newpassword/newpassword.component';
import { ChangepasswordComponent } from './components/changepassword/changepassword.component';
import { NoteComponent } from './components/note/note.component';
import { ArchiveComponent } from './components/archive/archive.component';
import { TrashComponent } from './components/trash/trash.component';
import { ReminderComponent } from './components/reminder/reminder.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forgotpassword', component: NewpasswordComponent },
  { path: 'home', component: HomeComponent,
  children: [
    { path: '', redirectTo: 'note', pathMatch: 'full' },
    { path: 'note', component: NoteComponent},
    { path: 'archive', component: ArchiveComponent},
    { path: 'trash', component: TrashComponent},
    { path: 'reminder', component: ReminderComponent}
  ] },
  { path: 'changepassword/:token', component: ChangepasswordComponent },
  { path: '**', redirectTo: '/login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LoginComponent,
  RegisterComponent,
  NewpasswordComponent,
  HomeComponent, ChangepasswordComponent]