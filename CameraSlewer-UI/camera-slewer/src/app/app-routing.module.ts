import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CameraSlewerComponent } from './camera-slewer/camera-slewer.component';
import { CameraComponent } from './camera/camera.component';
import { LocationComponent } from './location/location.component';

const routes: Routes = [
  { path: '', redirectTo: 'camera', pathMatch: 'full'},
  { path: 'camera', component: CameraComponent },
  { path: 'location', component: LocationComponent },
  { path: 'slewer', component: CameraSlewerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
