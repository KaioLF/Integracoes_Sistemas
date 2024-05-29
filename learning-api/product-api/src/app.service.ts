import { Injectable } from '@nestjs/common';
import { Product } from './models/Product';
import { InjectModel } from '@nestjs/sequelize';
import { CreateProductsDto } from './dto/create-products.dto';

@Injectable()
export class AppService {
  constructor(
    @InjectModel(Product)
    private productModel: typeof Product,
  ) {}

  async createMultipleProducts(createProductsDto: CreateProductsDto) {
    const { products } = createProductsDto;
    return await this.productModel.bulkCreate(products);
  }
}
