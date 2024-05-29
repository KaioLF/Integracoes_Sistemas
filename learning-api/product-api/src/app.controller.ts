import { Controller, Get, Post, Put, Body, Query, HttpCode, HttpStatus, Delete } from '@nestjs/common';
import { AppService } from './app.service';
import { CreateProductsDto, CreateProductDto } from './dto/create-products.dto';

@Controller('products')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  @HttpCode(HttpStatus.OK)
  public getProducts(@Query('name') name: string) {
    return this.appService.selectProduct(name);
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  public createProduct(@Body() body: CreateProductDto) {
    this.appService.createProduct(body);
    return { message: 'Product created', body };
  }

  @Put()
  @HttpCode(HttpStatus.OK)
  public updateProduct(@Body() body: CreateProductDto, @Query('id') id: number) {
    this.appService.updateProduct(body, id);
    return { message: 'Product updated', body };
  }

  @Delete()
  @HttpCode(HttpStatus.OK)
  public deleteProduct(@Query('id') id: number) {
    this.appService.deleteProduct(id);
    return { message: 'Product deleted' };
  }

  @Post('batch')
  @HttpCode(HttpStatus.CREATED)
  public async createMultipleProducts(@Body() body: CreateProductsDto) {
    await this.appService.createMultipleProducts(body);
    return { message: 'Products created', body };
  }
}
