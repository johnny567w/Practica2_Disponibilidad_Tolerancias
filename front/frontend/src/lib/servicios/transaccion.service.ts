import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transaccion } from '../../app/modelos/transaccion';
import { Observable } from 'rxjs';
import { TransaccionDTO } from '../../app/modelos/Transaccion-dto';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {
private baseUrl = '/api/transacciones';

  constructor(private http: HttpClient) {}

  depositar(cuentaId: number, monto: number): Observable<any> {
    const dto = { cuentaOrigenId: cuentaId, monto } as TransaccionDTO;
    return this.http.post(`${this.baseUrl}/deposito`, dto);
  }

  retirar(cuentaId: number, monto: number): Observable<any> {
    const dto = { cuentaOrigenId: cuentaId, monto } as TransaccionDTO;
    return this.http.post(`${this.baseUrl}/retiro`, dto);
  }

  transferir(origenId: number, destinoId: number, monto: number): Observable<any> {
    const dto = { cuentaOrigenId: origenId, cuentaDestinoId: destinoId, monto } as TransaccionDTO;
    return this.http.post(`${this.baseUrl}/transferencia`, dto);
  }

  listar(): Observable<TransaccionDTO[]> {
    return this.http.get<TransaccionDTO[]>(this.baseUrl);
  }
}
