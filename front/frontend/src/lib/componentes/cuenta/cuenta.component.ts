import { Component, OnInit } from '@angular/core';
import { Cuenta } from '../../../app/modelos/cuenta';
import { Cliente } from '../../../app/modelos/cliente';
import { CuentaService } from '../../servicios/cuenta.service';
import { ClienteService } from '../../servicios/cliente.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule,Validators } from '@angular/forms';
import { CuentaDTO } from '../../../app/modelos/cuenta-dto';
import { ClienteDTO } from '../../../app/modelos/cliente-dto';

@Component({
  selector: 'app-cuenta',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './cuenta.component.html',
  styleUrl: './cuenta.component.css'
})
export class CuentaComponent implements OnInit {

  cuentaForm: FormGroup;
  cuentas: Cuenta[] = [];
  clientes: ClienteDTO[] = [];

  mostrarFormulario: boolean = false;
  nuevaCuentaId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private cuentaService: CuentaService,
    private clienteService: ClienteService
  ) {
this.cuentaForm = this.fb.group({
  tipo: ['', Validators.required],
  saldo: [0, Validators.required],
  cliente: [null, Validators.required] 
});


  }

  ngOnInit(): void {
    this.listarCuentas();
    this.listarClientes();
  }

  listarCuentas(): void {
    this.cuentaService.listar().subscribe({
      next: data => this.cuentas = data,
      error: err => console.error('Error al listar cuentas:', err)
    });
  }

  listarClientes(): void {
    this.clienteService.listar().subscribe({
      next: data => this.clientes = data,
      error: err => console.error('Error al listar clientes:', err)
    });
  }

  toggleForm(): void {
    this.mostrarFormulario = !this.mostrarFormulario;
    if (!this.mostrarFormulario) this.resetFormulario();
  }
guardarCuenta(): void {
  if (this.cuentaForm.invalid) {
    alert('Por favor completa todos los campos correctamente.');
    return;
  }

  const formValue = this.cuentaForm.value;

const cuentaDTO: CuentaDTO = {
  tipo: this.cuentaForm.value.tipo,
  saldo: this.cuentaForm.value.saldo,
  clienteId: this.cuentaForm.value.cliente  
};

  if (this.nuevaCuentaId) {
    this.cuentaService.actualizarCuenta({ ...cuentaDTO, id: this.nuevaCuentaId }).subscribe({
      next: () => {
        this.listarCuentas();
        this.resetFormulario();
      },
      error: err => console.error('Error al actualizar cuenta:', err)
    });
  } else {
    console.log('Cuenta a enviar:', cuentaDTO);
    this.cuentaService.guardarCuenta(cuentaDTO).subscribe({
      next: () => {
        this.listarCuentas();
        this.resetFormulario();
      },
      error: err => console.error('Error al crear cuenta:', err)
    });
  }
}



  editarCuenta(cuenta: Cuenta): void {
    this.mostrarFormulario = true;
    this.nuevaCuentaId = cuenta.id!;
this.cuentaForm.setValue({
  clienteId: cuenta.cliente.id,
  tipo: cuenta.tipo,
  saldo: cuenta.saldo
});

  }

  eliminarCuenta(id: number): void {
    if (confirm('¿Estás seguro de eliminar esta cuenta?')) {
      this.cuentaService.eliminar(id).subscribe({
        next: () => this.listarCuentas(),
        error: err => console.error('Error al eliminar cuenta:', err)
      });
    }
  }

  resetFormulario(): void {
    this.cuentaForm.reset({
      clienteId: null,
      tipo: '',
      saldo: 0
    });
    this.nuevaCuentaId = null;
    this.mostrarFormulario = false;
  }

  cancelar(): void {
    this.resetFormulario();
  }
}