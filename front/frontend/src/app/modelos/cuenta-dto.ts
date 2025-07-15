export interface CuentaDTO {
  id?: number;
  tipo: string;
  saldo: number;
  clienteId: number;
  clienteNombre?: string; // opcional, solo para mostrar en tabla
}
