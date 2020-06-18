import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'association-12',
        loadChildren: () => import('./association-12/association-12.module').then(m => m.Transaction2Association12Module),
      },
      {
        path: 'catonlinecanalenum',
        loadChildren: () => import('./catonlinecanalenum/catonlinecanalenum.module').then(m => m.Transaction2CatonlinecanalenumModule),
      },
      {
        path: 'clientglentity',
        loadChildren: () => import('./clientglentity/clientglentity.module').then(m => m.Transaction2ClientglentityModule),
      },
      {
        path: 'formulationentity',
        loadChildren: () => import('./formulationentity/formulationentity.module').then(m => m.Transaction2FormulationentityModule),
      },
      {
        path: 'modeoperationenum',
        loadChildren: () => import('./modeoperationenum/modeoperationenum.module').then(m => m.Transaction2ModeoperationenumModule),
      },
      {
        path: 'pdventity',
        loadChildren: () => import('./pdventity/pdventity.module').then(m => m.Transaction2PdventityModule),
      },
      {
        path: 'statutticketenum',
        loadChildren: () => import('./statutticketenum/statutticketenum.module').then(m => m.Transaction2StatutticketenumModule),
      },
      {
        path: 'typeannulationenum',
        loadChildren: () => import('./typeannulationenum/typeannulationenum.module').then(m => m.Transaction2TypeannulationenumModule),
      },
      {
        path: 'typecanalenum',
        loadChildren: () => import('./typecanalenum/typecanalenum.module').then(m => m.Transaction2TypecanalenumModule),
      },
      {
        path: 'typeoperationenum',
        loadChildren: () => import('./typeoperationenum/typeoperationenum.module').then(m => m.Transaction2TypeoperationenumModule),
      },
      {
        path: 'typepaiementenum',
        loadChildren: () => import('./typepaiementenum/typepaiementenum.module').then(m => m.Transaction2TypepaiementenumModule),
      },
      {
        path: 'typeterminalenum',
        loadChildren: () => import('./typeterminalenum/typeterminalenum.module').then(m => m.Transaction2TypeterminalenumModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Transaction2EntityModule {}
