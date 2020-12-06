import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Camera} from '../models/camera';
import {Location} from '../models/location';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { CameraRequest } from './requests/addCameraRequest';
import { CameraResponse } from './response/cameraResponse';
import { LocationResponse } from './response/locationResponse';
import { LocationRequest } from './requests/addLocationRequest';
import { GetNearByResponse } from './response/getNearByresponse';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root',
  })
export class DataService {
  private readonly API_URL = 'http://localhost:8080/location/';
  dataChange: BehaviorSubject<Camera[]> = new BehaviorSubject<Camera[]>([]);
  locationDataChange: BehaviorSubject<Location[]> = new BehaviorSubject<Location[]>([]);
  nearestLocations: BehaviorSubject<GetNearByResponse> = new BehaviorSubject<GetNearByResponse>(new GetNearByResponse());
  // Temporarily stores data from dialogs
  dialogData: any;

  locationDialogData: any;

  constructor (private httpClient: HttpClient,
    private route:Router) {}


  get data(): Camera[] {
    return this.dataChange.value;
  }

  get locationData(): Location[] {
    return this.locationDataChange.value;
  }

  get nearestLocation(): GetNearByResponse {
    return this.nearestLocations.value;
  }

  getDialogData() {
    return this.dialogData;
  }

  getLocationDialogData() {
    return this.locationDialogData;
  }

  /** CRUD METHODS */
  getAllCameras(): void {
    this.httpClient.get<CameraResponse>(this.API_URL+'listCamera').subscribe(extractData => {
        this.dataChange.next(extractData.camerraList);
        this.route.navigate(['/camera']);
      },
      (error: HttpErrorResponse) => {
      console.log (error.name + ' ' + error.message);
      });
  }

  addCamera(camera: Camera): void { 
    const cameraRequest: CameraRequest  = new CameraRequest(); 
    cameraRequest.camera = camera;
    this.httpClient.post(this.API_URL+'addCamera', cameraRequest).subscribe(data => {
      this.dialogData = camera;
      this.getAllCameras();
     
      //this.toasterService.showToaster('Successfully added', 3000);
      },
      (err: HttpErrorResponse) => {
     // this.toasterService.showToaster('Error occurred. Details: ' + err.name + ' ' + err.message, 8000);
    });
 }

 getAllLocations(): void {
    this.httpClient.get<LocationResponse>(this.API_URL+'listLocations').subscribe(extractData => {
        this.locationDataChange.next(extractData.locations);
      },
      (error: HttpErrorResponse) => {
      console.log (error.name + ' ' + error.message);
      });
  }

  addLocation(location: Location): void {
    const locationRequest: LocationRequest  = new LocationRequest(); 
    locationRequest.location = location;
    this.httpClient.post(this.API_URL+'addLocation', locationRequest).subscribe(data => {
      this.locationDialogData = location;
      this.getAllLocations();
      
      //this.toasterService.showToaster('Successfully added', 3000);
      },
      (err: HttpErrorResponse) => {
     // this.toasterService.showToaster('Error occurred. Details: ' + err.name + ' ' + err.message, 8000);
    });
 }

 getNearestLocations(camera: Camera): void { 
  const cameraRequest: CameraRequest  = new CameraRequest(); 
  cameraRequest.camera = camera;
  this.httpClient.post<GetNearByResponse>(this.API_URL+'getNearByLocation', cameraRequest).subscribe(extractData => {
    extractData.camera = camera;
    this.nearestLocations.next(extractData);
    this.route.navigate(['/slewer']);
    //this.toasterService.showToaster('Successfully added', 3000);
    },
    (err: HttpErrorResponse) => {
   // this.toasterService.showToaster('Error occurred. Details: ' + err.name + ' ' + err.message, 8000);
  });
}

}
