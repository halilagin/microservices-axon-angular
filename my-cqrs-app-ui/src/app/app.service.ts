import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://localhost:8039';

  getProducts() {
    return this.http.get(this.rootURL + '/products');
  }

  addProduct(product: any) {
    return this.http.post(this.rootURL + '/addProduct', product);
  }

}
