import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CameraComponent } from './camera/camera.component';
import { LocationComponent } from './location/location.component';
import { AddDialogComponent } from './add-dialog/add-dialog.component';
import { RouterModule } from '@angular/router';
import { AddLocationDialogComponent } from './add-location-dialog/add-location-dialog.component';
import { CameraSlewerComponent } from './camera-slewer/camera-slewer.component';
import { MatOption, MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    AppComponent,
    CameraComponent,
    LocationComponent,
    AddDialogComponent,
    AddLocationDialogComponent,
    CameraSlewerComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatDialogModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatOptionModule
    

  ],
  entryComponents: [
    AddDialogComponent
  ],
  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }
