import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { TypeoperationenumComponent } from './typeoperationenum.component';
import { TypeoperationenumDetailComponent } from './typeoperationenum-detail.component';
import { TypeoperationenumUpdateComponent } from './typeoperationenum-update.component';
import { TypeoperationenumDeleteDialogComponent } from './typeoperationenum-delete-dialog.component';
import { typeoperationenumRoute } from './typeoperationenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(typeoperationenumRoute)],
  declarations: [
    TypeoperationenumComponent,
    TypeoperationenumDetailComponent,
    TypeoperationenumUpdateComponent,
    TypeoperationenumDeleteDialogComponent,
  ],
  entryComponents: [TypeoperationenumDeleteDialogComponent],
})
export class Transaction2TypeoperationenumModule {}
