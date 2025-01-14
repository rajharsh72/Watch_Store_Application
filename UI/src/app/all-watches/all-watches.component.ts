import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AdminServiceService } from '../service/admin-service.service';
import { UserServiceService } from '../service/user-service.service';
import { UpdateWatchComponent } from '../update-watch/update-watch.component';

@Component({
  selector: 'app-all-watches',
  templateUrl: './all-watches.component.html',
  styleUrls: ['./all-watches.component.css']
})
export class AllWatchesComponent {

  constructor(
    private _userService:UserServiceService,
    private _fb:FormBuilder,
    private _dialog:MatDialog,
    private _adminService: AdminServiceService,
  ){}

  allWatches:any;
  filteredWatches:any;
  brands: any[]=[];
  types:any[]=[];
  currentRole = this._userService.getCurrentRole();

  sortOptions = [
    {value: 'latestArrival', viewValue: 'Latest Arrival'},
    {value: 'priceLowToHigh', viewValue: 'Price Low to High'},
    {value: 'priceHighToLow', viewValue: 'Price High to Low'},
  ]

  sortForm = this._fb.group({
    sort: ['']
  })
 

  filterForm = this._fb.group({
    brand: [''],
    priceRange:[''],
    type:['']

  })

  
  

  ngOnInit():void{
    this._userService.getAllWatches().subscribe((data:any)=>{
      this.allWatches=data;
      this.filteredWatches=data;
      this.extractFilterOptions();
      this.filterForm.valueChanges.subscribe(() => this.applyFilters());
      this.sortForm.valueChanges.subscribe(() => this.applySorting());
    },
    (err)=>{
      console.log(err);
    });
  }


  extractFilterOptions():void{
    this.brands = [...new Set(this.allWatches.map((watch: { brand: any; }) => watch.brand))];
    this.types =  [...new Set(this.allWatches.map((watch: { type: any; }) => watch.type))];
  }

  applyFilters(): void {
    this.filteredWatches = this.allWatches.filter((watch: { brand: string; type: string; price: number; }) => {
      const brandMatch = !this.filterForm.value.brand || watch.brand === this.filterForm.value.brand;
      const typeMatch = !this.filterForm.value.type || watch.type === this.filterForm.value.type;
      let priceMatch = true;

      if (this.filterForm.value.priceRange) {
        const [min, max] = this.filterForm.value.priceRange.split('-').map(Number);
        if (max === 999999) {

          priceMatch = watch.price >= min;
        } else {
          priceMatch = watch.price >= min && watch.price <= max;
        }
      }

      return brandMatch && typeMatch && priceMatch;
    });
    this.sortForm.reset();
  }

  applySorting(){
    if(this.sortForm.value.sort === 'latestArrival'){
      this._userService.getAllWatchesBasedOnLatestArrival().subscribe((data)=>{
        console.log(data);
        this.filteredWatches=data;
      },
      (err)=>{
  
      });
    }
  }

  openUpdateDialog(watch:any){
    this._dialog.open(UpdateWatchComponent,{
      data: {watch}
    });
  }

  markOutOfStock(watchId:any){
    if(confirm(`Are you sure you want to mark it OUT OF STOCK?`)){
      this._adminService.markOutOfStock(watchId).subscribe((res)=>{
        console.log(res);
      },
      (err)=>{
        console.log(err);
      })
    }
  }

}
