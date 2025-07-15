import { Banco } from "./banco";

export interface Cliente {
  id?: number;
  nombre: string;
  correo: string;
  banco: Banco;
}
