import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../../app/modelos/transaccion';
import { BancoService } from '../../servicios/banco.service';
import { ClienteService } from '../../servicios/cliente.service';
import { CuentaService } from '../../servicios/cuenta.service';
import { TransaccionService } from '../../servicios/transaccion.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { TransaccionDTO } from '../../../app/modelos/Transaccion-dto';

@Component({
  selector: 'app-dashboard',
  imports: [    FormsModule,
    HttpClientModule,CommonModule,NavbarComponent,RouterModule,CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  bancosCount: number = 0;
  clientesCount: number = 0;
  cuentasCount: number = 0;
  transacciones: TransaccionDTO[] = [];

  constructor(
    private bancoService: BancoService,
    private clienteService: ClienteService,
    private cuentaService: CuentaService,
    private transaccionService: TransaccionService
  ) {}

  ngOnInit(): void {
    this.obtenerBancos();
    this.obtenerClientes();
    this.obtenerCuentas();
    this.obtenerTransacciones();
  }

  obtenerBancos() {
    this.bancoService.listar().subscribe(bancos => {
      this.bancosCount = bancos.length;
    });
  }

  obtenerClientes() {
    this.clienteService.listar().subscribe(clientes => {
      this.clientesCount = clientes.length;
    });
  }

  obtenerCuentas() {
    this.cuentaService.listar().subscribe(cuentas => {
      this.cuentasCount = cuentas.length;
    });
  }

  obtenerTransacciones() {
    this.transaccionService.listar().subscribe(transacciones => {
      this.transacciones = transacciones.slice(-5).reverse();
    });
  }
}