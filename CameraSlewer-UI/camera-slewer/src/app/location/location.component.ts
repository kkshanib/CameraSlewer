import { HttpClient } from '@angular/common/http';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {BehaviorSubject, fromEvent, merge, Observable} from 'rxjs';
import { Location } from '../models/location';
import { DataService } from '../service/dataService';
import {DataSource} from '@angular/cdk/collections';
import {map} from 'rxjs/operators';
import { AddLocationDialogComponent } from '../add-location-dialog/add-location-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  displayedColumns = ['locationName', 'latitude', 'longitude', 'actions'];
  exampleDatabase: DataService | null;
  dataSource: ExampleDataSource | null;
  index: number;
  id: number;

  constructor(public dialog: MatDialog,
              public httpClient: HttpClient,
              public dataService: DataService,
              public route: Router
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
    const dialogRef = this.dialog.open(AddLocationDialogComponent, {
      data: {location: Location }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.refreshTable();
       }
       setTimeout(() => {this.refresh();}, 2000);
    });
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

export class ExampleDataSource extends DataSource<Location> {
  _filterChange = new BehaviorSubject('');

  get filter(): string {
    return this._filterChange.value;
  }

  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  filteredData: Location[] = [];
  renderedData: Location[] = [];

  constructor(public _exampleDatabase: DataService,
              public _paginator: MatPaginator,
              public _sort: MatSort) {
    super();
      this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  connect(): Observable<Location[]> {
    const displayLocationDataChanges = [
      this._exampleDatabase.locationDataChange,
      this._sort.sortChange,
      this._filterChange,
      this._paginator.page
    ];

    this._exampleDatabase.getAllLocations();

    return merge(...displayLocationDataChanges).pipe(map( () => {
       if(this._exampleDatabase && this._exampleDatabase.locationData) {
          this.filteredData = this._exampleDatabase.locationData.slice().filter((location: Location) => {
          const searchStr = (location.locationName + location.longitude + location.longitude).toLowerCase();
          return searchStr.indexOf(this.filter.toLowerCase()) !== -1;
          const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
          this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
        });
        const sortedData = this.sortData(this.filteredData.slice());
        const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
        this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);

       }
        return this.renderedData;
      }
    ));
  }

  disconnect() {}
  sortData(data: Location[]): Location[] {
    if (!this._sort.active || this._sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      let propertyA: number | string = '';
      let propertyB: number | string = '';

      switch (this._sort.active) {
        case 'locationName': [propertyA, propertyB] = [a.locationName, b.locationName]; break;
        case 'latitude': [propertyA, propertyB] = [a.latitude, b.latitude]; break;
        case 'longitude': [propertyA, propertyB] = [a.longitude, b.longitude]; break;
      }

      const valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      const valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction === 'asc' ? 1 : -1);
    });
  }
}
