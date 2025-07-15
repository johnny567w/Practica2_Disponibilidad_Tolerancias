import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cuenta } from '../../app/modelos/cuenta';
import { CuentaDTO } from '../../app/modelos/cuenta-dto';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {

private baseUrl = '/api/cuentas';

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  listar(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(this.baseUrl);
  }

  guardarCuenta(cuentaDTO: CuentaDTO): Observable<Cuenta> {
    return this.http.post<Cuenta>(this.baseUrl, cuentaDTO, this.httpOptions);
  }

  actualizarCuenta(cuentaDTO: CuentaDTO & { id: number }): Observable<Cuenta> {
    return this.http.put<Cuenta>(`${this.baseUrl}/${cuentaDTO.id}`, cuentaDTO, this.httpOptions);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  obtenerPorId(id: number): Observable<Cuenta> {
    return this.http.get<Cuenta>(`${this.baseUrl}/${id}`);
  }
}