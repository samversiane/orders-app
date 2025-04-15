import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {OrderItem} from '../../models/order-model';
import {OrderService} from '../../services/order.service';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule
  ]
})
export class OrderItemsComponent implements OnInit, AfterViewInit {
  @Input() orderId!: number;

  displayedColumns: string[] = ['id', 'product', 'price', 'quantity', 'total'];
  dataSource = new MatTableDataSource<OrderItem>([]);

  totalElements = 0;
  pageSize = 5;
  currentPage = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.loadOrderItems();
  }

  ngAfterViewInit() {
    this.paginator.page.subscribe(event => {
      this.currentPage = event.pageIndex;
      this.pageSize = event.pageSize;
      this.loadOrderItems();
    });
  }

  loadOrderItems() {
    this.orderService.getOrderItems(this.orderId, this.currentPage, this.pageSize)
      .subscribe(response => {
        this.dataSource.data = response.content;
        this.totalElements = response.totalElements;
        this.pageSize = response.size;
        this.currentPage = response.number;
      });
  }

  calculateTotal(item: OrderItem): number {
    return item.quantity * item.product.price;
  }
}
