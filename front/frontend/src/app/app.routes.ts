import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BancoComponent } from '../lib/componentes/banco/banco.component';
import { ClienteComponent } from '../lib/componentes/cliente/cliente.component';
import { CuentaComponent } from '../lib/componentes/cuenta/cuenta.component';
import { TransaccionComponent } from '../lib/componentes/transaccion/transaccion.component';
import { DashboardComponent } from '../lib/componentes/dashboard/dashboard.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }, 
  { path: 'dashboard', component: DashboardComponent },
  { path: 'bancos', component: BancoComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'cuentas', component: CuentaComponent },
  { path: 'transacciones', component: TransaccionComponent },
  { path: '**', redirectTo: 'dashboard' } 
];