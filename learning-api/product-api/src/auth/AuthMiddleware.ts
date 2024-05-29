import { Injectable, NestMiddleware } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { Request, Response, NextFunction } from 'express';

@Injectable()
export class AuthMiddleware implements NestMiddleware {
    constructor(private readonly jwtService: JwtService) {}

    use(req: Request, res: Response, next: NextFunction) {
        const token = req.headers['authorization'];

        if (!token || !token.startsWith('Bearer ')) {
        return res.status(401).json({ message: 'Token de autenticação não fornecido' });
        }

        try {
        const decoded = this.jwtService.verify(token.substring(7)); // Remove "Bearer " do token
        req.user = decoded;
        next();
        } catch (err) {
        return res.status(401).json({ message: 'Token de autenticação inválido' });
        }
    }
}
