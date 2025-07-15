export interface ClienteDTO {
  id?: number;            // Opcional para creación
  nombre: string;
  correo: string;
  bancoId?: number;       // ID del banco seleccionado
  bancoNombre?: string;   // Nombre del banco (para mostrar en tabla)
}
