import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { AuthService } from './AuthService';

@Module({
    imports: [
        JwtModule.register({
        secret: 'seu_segredo_aqui',
        signOptions: { expiresIn: '1h' }, // Define o tempo de expiração do token
        }),
    ],
    providers: [AuthService],
    exports: [AuthService],
})
export class AuthModule {}
