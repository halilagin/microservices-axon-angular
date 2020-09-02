import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from './app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {

  constructor(private appService: AppService) {}

  title = 'e-commerce example';

  userForm = new FormGroup({
    stock: new FormControl('', Validators.nullValidator && Validators.required),
    price: new FormControl('', Validators.nullValidator && Validators.required),
    description: new FormControl('', Validators.nullValidator && Validators.required)
  });

  products: any[] = [];

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {

    this.appService.addProduct(this.userForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      console.log('message::::', data);
      this.userForm.reset();
    });
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
