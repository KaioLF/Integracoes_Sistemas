import { IsString, IsNumber, IsArray, ValidateNested } from 'class-validator';
import { Type } from 'class-transformer';

export class CreateProductDto {
    @IsString()
    name: string;

    @IsString()
    description: string;

    @IsString()
    company: string;

    @IsNumber()
    price: number;

    @IsNumber()
    amount: number;
}

export class CreateProductsDto {
    @IsString()
    user: string;

    @IsString()
    password: string;

    @IsArray()
    @ValidateNested({ each: true })
    @Type(() => CreateProductDto)
    products: CreateProductDto[];
}
