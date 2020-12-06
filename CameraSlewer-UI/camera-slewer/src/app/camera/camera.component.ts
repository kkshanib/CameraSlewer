import { HttpClient } from '@angular/common/http';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {BehaviorSubject, fromEvent, merge, Observable} from 'rxjs';
import { AddDialogComponent } from '../add-dialog/add-dialog.component';
import { Camera } from '../models/camera';
import { DataService } from '../service/dataService';
import {DataSource} from '@angular/cdk/collections';
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';


@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrls: ['./camera.component.css']
})
export class CameraComponent implements OnInit {

  displayedColumns = ['name', 'cameraType', 'latitude', 'longitude', 'azimuth', 'actions'];
  exampleDatabase: DataService | null;
  dataSource: ExampleDataSource | null;
  index: number;
  id: number;

  constructor(public dialog: MatDialog,
              public httpClient: HttpClient,
              public route: Router,
              public dataService: DataService
    ) { }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild('filter',  {static: true}) filter: ElementRef;

  ngOnInit() {
    this.loadData();
  }

  refresh() {
    this.loadData();
  }

  addNew() {
    const dialogRef = this.dialog.open(AddDialogComponent, {
      data: {camera: Camera }
    });

    dialogRef.afterClosed().subscribe(result => {
     // if (result === 1) {
        this.refreshTable();
        setTimeout(() => {this.refresh();}, 2000);
     // }
    });
  }

  slewToNearest(i, id, name, longitude, latitude, azimuth) {
    const camera: Camera = new Camera();
    camera.id = id;
    camera.name = name;
    camera.longitude = longitude;
    camera.latitude = latitude;
    camera.azimuth = azimuth;
    this.dataService.getNearestLocations(camera);
  }


  private refreshTable() {
    this.paginator._changePageSize(this.paginator.pageSize);
  }

  public loadData() {
    this.exampleDatabase = new DataService(this.httpClient, this.route);
    this.dataSource = new ExampleDataSource(this.exampleDatabase, this.paginator, this.sort);

    fromEvent(this.filter.nativeElement, 'keyup')
      .subscribe(() => {
        if (!this.dataSource) {
          return;
        }
        this.dataSource.filter = this.filter.nativeElement.value;
      });
  }
}

export class ExampleDataSource extends DataSource<Camera> {
  _filterChange = new BehaviorSubject('');

  get filter(): string {
    return this._filterChange.value;
  }

  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  filteredData: Camera[] = [];
  renderedData: Camera[] = [];

  constructor(public _exampleDatabase: DataService,
              public _paginator: MatPaginator,
              public _sort: MatSort) {
    super();
      this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<Camera[]> {
    // Listen for any changes in the base data, sorting, filtering, or pagination
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.sortChange,
      this._filterChange,
      this._paginator.page
    ];

    this._exampleDatabase.getAllCameras();

    return merge(...displayDataChanges).pipe(map( () => {
        // Filter data
       if(this._exampleDatabase && this._exampleDatabase.data) {
          this.filteredData = this._exampleDatabase.data.slice().filter((camera: Camera) => {
          const searchStr = (camera.name + camera.cameraType + camera.longitude + camera.longitude).toLowerCase();
          return searchStr.indexOf(this.filter.toLowerCase()) !== -1;
          const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
          this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
        });

        // Sort filtered data
        const sortedData = this.sortData(this.filteredData.slice());
        const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
        this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);

       }
        return this.renderedData;
      }
    ));
  }

  disconnect() {}
  sortData(data: Camera[]): Camera[] {
    if (!this._sort.active || this._sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      let propertyA: number | string = '';
      let propertyB: number | string = '';

      switch (this._sort.active) {
        case 'name': [propertyA, propertyB] = [a.name, b.name]; break;
        case 'cameraType': [propertyA, propertyB] = [a.cameraType, b.cameraType]; break;
        case 'latitude': [propertyA, propertyB] = [a.latitude, b.latitude]; break;
        case 'longitude': [propertyA, propertyB] = [a.longitude, b.longitude]; break;
        case 'azimuth': [propertyA, propertyB] = [a.azimuth, b.azimuth]; break;
      }

      const valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      const valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction === 'asc' ? 1 : -1);
    });
  }

 
  

}
