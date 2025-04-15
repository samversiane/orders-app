import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PaginatedResponse, OrderModel, OrderItem} from '../models/order-model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getOrders(page: number, size: number): Observable<PaginatedResponse<OrderModel>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PaginatedResponse<OrderModel>>(`${this.baseUrl}/order`, {params});
  }

  getOrderItems(orderId: number, page: number, size: number): Observable<PaginatedResponse<OrderItem>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PaginatedResponse<OrderItem>>(`${this.baseUrl}/order-item/${orderId}`, {params});
  }
}
