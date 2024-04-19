import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AppUserController } from './app.usercontroller';

@Module({
  imports: [],
  controllers: [AppController, AppUserController],
  providers: [AppService],
})
export class AppModule {}
