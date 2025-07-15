import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Banco } from '../../app/modelos/banco';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class BancoService {

private baseUrl = '/api/bancos';  // NO usar environment aquí en producción

  constructor(private http: HttpClient) { }

  listar(): Observable<Banco[]> {
    return this.http.get<Banco[]>(this.baseUrl);
  }

  crear(banco: Banco): Observable<Banco> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Banco>(this.baseUrl, banco, { headers: headers });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  actualizarBanco(banco: Banco): Observable<Banco> {
  return this.http.put<Banco>(`${this.baseUrl}/${banco.id}`, banco);
}

}