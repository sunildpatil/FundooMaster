import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  close(title, description) {
    if(title==this.data.title && description==this.data.description){
      this.dialogRef.close();
    }else{
      this.data.title = title;
      this.data.description = description;
      this.dialogRef.close(this.data);
    }
  }

}
