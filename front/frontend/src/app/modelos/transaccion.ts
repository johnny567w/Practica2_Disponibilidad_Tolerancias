import { Cuenta } from "./cuenta";

export interface Transaccion {
  id?: number;
  tipo: string;
  monto: number;
  fecha: string;
  cuentaOrigen?: Cuenta;
  cuentaDestino?: Cuenta;
}
