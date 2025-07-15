import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Banco } from '../../app/modelos/banco';

@Injectable({
  providedIn: 'root'
})
export class BancoService {

  private apiUrl = 'http://localhost:8080/bancos';

  constructor(private http: HttpClient) { }

  listar(): Observable<Banco[]> {
    return this.http.get<Banco[]>(this.apiUrl);
  }

  crear(banco: Banco): Observable<Banco> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Banco>(this.apiUrl, banco, { headers: headers });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  actualizarBanco(banco: Banco): Observable<Banco> {
  return this.http.put<Banco>(`${this.apiUrl}/${banco.id}`, banco);
}

}