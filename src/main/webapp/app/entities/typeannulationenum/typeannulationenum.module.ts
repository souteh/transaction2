import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { TypeannulationenumComponent } from './typeannulationenum.component';
import { TypeannulationenumDetailComponent } from './typeannulationenum-detail.component';
import { TypeannulationenumUpdateComponent } from './typeannulationenum-update.component';
import { TypeannulationenumDeleteDialogComponent } from './typeannulationenum-delete-dialog.component';
import { typeannulationenumRoute } from './typeannulationenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(typeannulationenumRoute)],
  declarations: [
    TypeannulationenumComponent,
    TypeannulationenumDetailComponent,
    TypeannulationenumUpdateComponent,
    TypeannulationenumDeleteDialogComponent,
  ],
  entryComponents: [TypeannulationenumDeleteDialogComponent],
})
export class Transaction2TypeannulationenumModule {}
