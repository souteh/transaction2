import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { FormulationentityComponent } from './formulationentity.component';
import { FormulationentityDetailComponent } from './formulationentity-detail.component';
import { FormulationentityUpdateComponent } from './formulationentity-update.component';
import { FormulationentityDeleteDialogComponent } from './formulationentity-delete-dialog.component';
import { formulationentityRoute } from './formulationentity.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(formulationentityRoute)],
  declarations: [
    FormulationentityComponent,
    FormulationentityDetailComponent,
    FormulationentityUpdateComponent,
    FormulationentityDeleteDialogComponent,
  ],
  entryComponents: [FormulationentityDeleteDialogComponent],
})
export class Transaction2FormulationentityModule {}
