import { Component } from '@angular/core';
import { AdminServiceService } from '../service/admin-service.service';
import { ChartData, ChartType } from 'chart.js';

@Component({
  selector: 'app-stock-stats',
  templateUrl: './stock-stats.component.html',
  styleUrls: ['./stock-stats.component.css']
})
export class StockStatsComponent {
  constructor(
    private _adminService: AdminServiceService
  ){}

  stockStats: any
  public chartLabels: string[] =[]
  public chartData: ChartData<'pie', number[], string>={
    labels:[],
    datasets: [{data:[]}]
  };
  public chartType:ChartType = 'pie';

  ngOnInit(){
    this._adminService.getStockStats().subscribe((res) =>{
      console.log(res)
      this.stockStats = res;
      this.chartLabels = Object.keys(this.stockStats);
      
      this.chartData ={
        labels: this.chartLabels,
        datasets: [{data: Object.values(this.stockStats) as number[]}]
      }
      console.log(this.chartLabels)
      console.log(this.chartData)
      // this.chartLabels.push(this.stockStats[0], this.stockStats[1], this.stockStats[3])
      // console.log(this.chartLabels)

    },
    (err)=>{
      console.log(err);
    })
  }



}
