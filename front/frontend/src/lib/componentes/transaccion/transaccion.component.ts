import { Component, OnInit } from '@angular/core';
import { Cuenta } from '../../../app/modelos/cuenta';
import { Transaccion } from '../../../app/modelos/transaccion';
import { FormBuilder, FormGroup, ReactiveFormsModule,Validators } from '@angular/forms';
import { CuentaService } from '../../servicios/cuenta.service';
import { TransaccionService } from '../../servicios/transaccion.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-transaccion',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './transaccion.component.html',
  styleUrl: './transaccion.component.css'
})
export class TransaccionComponent implements OnInit {

  formularioVisible: string = ''; // 'deposito', 'retiro', 'transferencia'
  cuentas: Cuenta[] = [];

  depositoForm!: FormGroup;
  retiroForm!: FormGroup;
  transferenciaForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private transaccionService: TransaccionService,
    private cuentaService: CuentaService
  ) {}

  ngOnInit(): void {
    this.cargarCuentas();

    this.depositoForm = this.fb.group({
      cuentaId: [null],
      monto: [0]
    });

    this.retiroForm = this.fb.group({
      cuentaId: [null],
      monto: [0]
    });

    this.transferenciaForm = this.fb.group({
      origenId: [null],
      destinoId: [null],
      monto: [0]
    });
      this.cuentaService.listar().subscribe(cuentas => {
    this.cuentas = cuentas;
    console.log('Cuentas cargadas:', this.cuentas);  // 👈 AÑADE ESTA LÍNEA
  });
  }

  cargarCuentas(): void {
    this.cuentaService.listar().subscribe((data) => {
      this.cuentas = data;
    });
  }

  mostrarFormulario(tipo: string): void {
    this.formularioVisible = tipo;
  }

  cancelarFormulario(): void {
    this.formularioVisible = '';
    this.depositoForm.reset();
    this.retiroForm.reset();
    this.transferenciaForm.reset();
  }

  realizarDeposito(): void {
    const cuentaId = this.depositoForm.value.cuentaId;
    const monto = this.depositoForm.value.monto;

    this.transaccionService.depositar(cuentaId, monto).subscribe(() => {
      alert('Depósito realizado');
      this.cancelarFormulario();
      this.cargarCuentas();
    });
  }

  realizarRetiro(): void {
    const cuentaId = this.retiroForm.value.cuentaId;
    const monto = this.retiroForm.value.monto;

    this.transaccionService.retirar(cuentaId, monto).subscribe(() => {
      alert('Retiro realizado');
      this.cancelarFormulario();
      this.cargarCuentas();
    });
  }

  realizarTransferencia(): void {
    const origenId = this.transferenciaForm.value.origenId;
    const destinoId = this.transferenciaForm.value.destinoId;
    const monto = this.transferenciaForm.value.monto;

    if (origenId === destinoId) {
      alert('La cuenta origen y destino no pueden ser la misma');
      return;
    }

    this.transaccionService.transferir(origenId, destinoId, monto).subscribe(() => {
      alert('Transferencia realizada');
      this.cancelarFormulario();
      this.cargarCuentas();
    });
  }
}