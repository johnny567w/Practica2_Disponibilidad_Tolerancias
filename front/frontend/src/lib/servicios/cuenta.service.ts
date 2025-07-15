import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cuenta } from '../../app/modelos/cuenta';
import { CuentaDTO } from '../../app/modelos/cuenta-dto';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {

  private apiUrl = 'http://localhost:8080/cuentas';

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  listar(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(this.apiUrl);
  }

  guardarCuenta(cuentaDTO: CuentaDTO): Observable<Cuenta> {
    return this.http.post<Cuenta>(this.apiUrl, cuentaDTO, this.httpOptions);
  }

  actualizarCuenta(cuentaDTO: CuentaDTO & { id: number }): Observable<Cuenta> {
    return this.http.put<Cuenta>(`${this.apiUrl}/${cuentaDTO.id}`, cuentaDTO, this.httpOptions);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  obtenerPorId(id: number): Observable<Cuenta> {
    return this.http.get<Cuenta>(`${this.apiUrl}/${id}`);
  }
}