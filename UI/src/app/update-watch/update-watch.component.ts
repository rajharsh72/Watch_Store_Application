import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdminServiceService } from '../service/admin-service.service';
import { DialogRef } from '@angular/cdk/dialog';
@Component({
  selector: 'app-update-watch',
  templateUrl: './update-watch.component.html',
  styleUrls: ['./update-watch.component.css']
})
export class UpdateWatchComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) private _watch:any,
    private _adminService:AdminServiceService,
    private _fb:FormBuilder,
    private _dialogRef:DialogRef<UpdateWatchComponent>,
  ){}

  updateForm!: FormGroup;

  ngOnInit(){
    this.initForm();
  }

  initForm(){
    console.log(this._watch);
    this.updateForm = this._fb.group({
      watchId: [this._watch.watch.watchId],
      brand: [this._watch.watch.brand, Validators.required],
      name:[this._watch.watch.name, Validators.required],
      type: [this._watch.watch.type, Validators.required],
      price: [this._watch.watch.price, Validators.required],
      description: [this._watch.watch.description, Validators.required],
      quantity: [this._watch.watch.quantity, [Validators.required, Validators.min(1)]],
      imageUrl: [this._watch.watch.imageUrl, Validators.required],
    });
    console.log(this.updateForm.value);
  }

  updateWatch(){
    if(this.updateForm.valid){
      this._adminService.updateWatch(this.updateForm.value, this._watch.watch.watchId).subscribe((res) =>{
        this._dialogRef.close();
        window.location.reload();
      },
      (err)=>{
        console.log(err);
      })
    }
  }
}
