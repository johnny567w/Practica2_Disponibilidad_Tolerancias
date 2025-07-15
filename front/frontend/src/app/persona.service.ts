import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Persona {
  id: number;
  nombre: string;
  apellido: string;
}

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  private apiUrl = '/api/personas'; 

  constructor(private http: HttpClient) {}

  getPersonas(): Observable<Persona[]> {
    return this.http.get<Persona[]>(this.apiUrl);
  }
  addPersona(persona: Partial<Persona>): Observable<Persona> {
  return this.http.post<Persona>(this.apiUrl, persona);
}

deletePersona(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}
}
