import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {OrdersTableComponent} from './pages/orders-table/orders-table.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, OrdersTableComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'orders-app-ui';
}
