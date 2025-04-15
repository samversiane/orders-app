export interface PaginatedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export interface OrderModel {
  id: number,
  customer: string,
  supplier: string,
  totalProducts: number,
  totalValue: number
}

export interface OrderItem {
  id: number;
  product: Product;
  quantity: number;
}

export interface Product {
  id: number;
  name: string;
  price: number;
}
