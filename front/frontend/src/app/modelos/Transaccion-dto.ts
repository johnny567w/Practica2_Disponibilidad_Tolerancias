export interface TransaccionDTO {
  id: number;
  cuentaOrigenId?: number;
  cuentaDestinoId?: number;
  monto: number;
  tipo: string;
  fecha: string;
}
