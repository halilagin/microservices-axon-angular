import { Component, OnInit, Input } from '@angular/core';
import { Subject } from 'rxjs';
import { AppService } from './app.service';
import { takeUntil } from 'rxjs/operators';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

    //products:any[];  
    constructor(private appService: AppService) {}

  destroy$: Subject<boolean> = new Subject<boolean>();

  products: any[];

  ngOnInit(): void {
  }
  getProducts() {
    this.appService.getProducts().pipe(takeUntil(this.destroy$)).subscribe((products: any[]) => {
        this.products = products;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
