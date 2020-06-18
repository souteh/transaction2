import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { ModeoperationenumComponent } from './modeoperationenum.component';
import { ModeoperationenumDetailComponent } from './modeoperationenum-detail.component';
import { ModeoperationenumUpdateComponent } from './modeoperationenum-update.component';
import { ModeoperationenumDeleteDialogComponent } from './modeoperationenum-delete-dialog.component';
import { modeoperationenumRoute } from './modeoperationenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(modeoperationenumRoute)],
  declarations: [
    ModeoperationenumComponent,
    ModeoperationenumDetailComponent,
    ModeoperationenumUpdateComponent,
    ModeoperationenumDeleteDialogComponent,
  ],
  entryComponents: [ModeoperationenumDeleteDialogComponent],
})
export class Transaction2ModeoperationenumModule {}
