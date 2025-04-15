import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {OrderModel} from '../../models/order-model';
import {OrderService} from '../../services/order.service';
import {OrderItemsComponent} from '../order-items/order-items.component';

@Component({
  selector: 'app-orders-table',
  templateUrl: './orders-table.component.html',
  styleUrls: ['./orders-table.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    OrderItemsComponent
  ],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class OrdersTableComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'customer', 'supplier', 'totalProducts', 'totalValue'];
  dataSource = new MatTableDataSource<OrderModel>([]);
  expandedElement: OrderModel | null = null;

  totalElements = 0;
  pageSize = 10;
  currentPage = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.loadOrders();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  loadOrders() {
    this.orderService.getOrders(this.currentPage, this.pageSize)
      .subscribe(response => {
        this.dataSource.data = response.content;
        this.totalElements = response.totalElements;
        this.pageSize = response.size;
        this.currentPage = response.number;
      });
  }

  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadOrders();
  }

  toggleRow(row: OrderModel) {
    this.expandedElement = this.expandedElement === row ? null : row;
  }
}
