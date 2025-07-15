import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../../app/modelos/cliente';
import { ClienteDTO } from '../../app/modelos/cliente-dto';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {

  private baseUrl = '/api/clientes';

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  listar(): Observable<ClienteDTO[]> {
    return this.http.get<ClienteDTO[]>(this.baseUrl);
  }

  crear(cliente: ClienteDTO): Observable<ClienteDTO> {
    return this.http.post<ClienteDTO>(this.baseUrl, cliente, this.httpOptions);
  }

  actualizar(cliente: ClienteDTO): Observable<ClienteDTO> {
    return this.http.put<ClienteDTO>(`${this.baseUrl}/${cliente.id}`, cliente, this.httpOptions);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
