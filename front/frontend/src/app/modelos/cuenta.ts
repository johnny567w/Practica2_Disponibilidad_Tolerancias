import { Cliente } from "./cliente";

export interface Cuenta {
  id?: number;
  tipo: string;
  saldo: number;
  cliente: Cliente; // Esto lo usabas antes

  // NUEVO campo opcional para cuando trabajas con DTO
  clienteId?: number;
  clienteNombre?: string;
}
