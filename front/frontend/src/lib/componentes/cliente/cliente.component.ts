import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../../app/modelos/cliente';
import { Banco } from '../../../app/modelos/banco';
import { ClienteService } from '../../servicios/cliente.service';
import { BancoService } from '../../servicios/banco.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ClienteDTO } from '../../../app/modelos/cliente-dto';

@Component({
  selector: 'app-cliente',
  imports: [FormsModule,           
    CommonModule],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css'
})
export class ClienteComponent implements OnInit {
  clientes: ClienteDTO[] = [];
  bancos: Banco[] = [];

  nuevoCliente: ClienteDTO = {
    nombre: '',
    correo: '',
    bancoId: undefined
  };

  modoEdicion: boolean = false;
  mostrarFormulario: boolean = false;

  constructor(
    private clienteService: ClienteService,
    private bancoService: BancoService
  ) {}

  ngOnInit(): void {
    this.obtenerClientes();
    this.obtenerBancos();
  }

  obtenerClientes(): void {
    this.clienteService.listar().subscribe({
      next: data => this.clientes = data,
      error: err => console.error('Error al obtener clientes', err)
    });
  }

  obtenerBancos(): void {
    this.bancoService.listar().subscribe({
      next: data => this.bancos = data,
      error: err => console.error('Error al obtener bancos', err)
    });
  }

  guardarCliente(): void {
    if (this.modoEdicion && this.nuevoCliente.id) {
      this.clienteService.actualizar(this.nuevoCliente).subscribe(() => {
        this.obtenerClientes();
        this.cancelar();
      });
    } else {
      this.clienteService.crear(this.nuevoCliente).subscribe(() => {
        this.obtenerClientes();
        this.cancelar();
      });
    }
  }

  editarCliente(cliente: ClienteDTO): void {
    this.nuevoCliente = {
      id: cliente.id,
      nombre: cliente.nombre,
      correo: cliente.correo,
      bancoId: cliente.bancoId
    };
    this.modoEdicion = true;
    this.mostrarFormulario = true;
  }

  eliminarCliente(id: number): void {
    this.clienteService.eliminar(id).subscribe(() => this.obtenerClientes());
  }

  toggleForm(): void {
    this.mostrarFormulario = !this.mostrarFormulario;
    if (!this.mostrarFormulario) this.cancelar();
  }

  cancelar(): void {
    this.nuevoCliente = {
      nombre: '',
      correo: '',
      bancoId: undefined
    };
    this.modoEdicion = false;
    this.mostrarFormulario = false;
  }
}