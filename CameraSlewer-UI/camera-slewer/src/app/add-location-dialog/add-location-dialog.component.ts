import { OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, Inject } from '@angular/core';
import { FormControl, Validators} from '@angular/forms';
import { Location } from '../models/location';
import { DataService } from '../service/dataService';

@Component({
  selector: 'app-add-location-dialog',
  templateUrl: './add-location-dialog.component.html',
  styleUrls: ['./add-location-dialog.component.css']
})
export class AddLocationDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<AddLocationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Location,
    public dataService: DataService) { }

ngOnInit() {

}

formControl = new FormControl('', [
Validators.required
// Validators.email,
]);

getErrorMessage() {
return this.formControl.hasError('required') ? 'Required field' : '';
}

submit() {
// emppty stuff
}

onNoClick(): void {
this.dialogRef.close();
}

public confirmAdd(): void {
this.dataService.addLocation(this.data);
}

}
