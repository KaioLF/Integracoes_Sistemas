import { Controller, Get } from '@nestjs/common';
import { AppDesignPatternsService } from './app.designpatternsservice'; 

@Controller('/designpatterns')
export class AppDesignPatternsController {
  public constructor(
    private readonly appDesignPatternsService: AppDesignPatternsService,
  ) {}
}