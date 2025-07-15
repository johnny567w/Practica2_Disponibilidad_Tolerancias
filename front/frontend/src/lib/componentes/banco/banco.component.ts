import { Component, OnInit } from '@angular/core';
import { Banco } from '../../../app/modelos/banco';
import { BancoService } from '../../servicios/banco.service';
import { FormsModule, NgModel } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-banco',
  imports: [    
    FormsModule,           
    CommonModule],
  templateUrl: './banco.component.html',
  styleUrl: './banco.component.css'
})
export class BancoComponent implements OnInit {

  bancos: Banco[] = [];
  nuevoBanco: Banco = { nombre: '' };
  showForm: boolean = false;

  constructor(private bancoService: BancoService) {}

  ngOnInit(): void {
    this.listarBancos();
  }

  listarBancos(): void {
    this.bancoService.listar().subscribe({
      next: data => this.bancos = data,
      error: err => console.error('Error al listar bancos:', err)
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.resetFormulario();
    }
  }

  guardarBanco(): void {
    if (!this.nuevoBanco.nombre || this.nuevoBanco.nombre.trim() === '') {
      alert('El nombre del banco no puede estar vacío.');
      return;
    }

    // Actualizar si existe ID
    if (this.nuevoBanco.id) {
      this.bancoService.actualizarBanco(this.nuevoBanco).subscribe({
        next: () => {
          this.listarBancos();
          this.resetFormulario();
        },
        error: err => console.error('Error al actualizar banco:', err)
      });
    } else {
      // Crear nuevo banco
      this.bancoService.crear(this.nuevoBanco).subscribe({
        next: () => {
          this.listarBancos();
          this.resetFormulario();
        },
        error: err => console.error('Error al crear banco:', err)
      });
    }
  }

  editarBanco(banco: Banco): void {
    this.nuevoBanco = { ...banco }; // Copiar banco para edición
    this.showForm = true;
  }

  eliminarBanco(id: number): void {
    if (!confirm('¿Estás seguro de que deseas eliminar este banco?')) return;

    this.bancoService.eliminar(id).subscribe({
      next: () => this.listarBancos(),
      error: err => console.error('Error al eliminar banco:', err)
    });
  }

  resetFormulario(): void {
    this.nuevoBanco = { nombre: '' };
    this.showForm = false;
  }
}