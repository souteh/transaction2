import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { TypecanalenumComponent } from './typecanalenum.component';
import { TypecanalenumDetailComponent } from './typecanalenum-detail.component';
import { TypecanalenumUpdateComponent } from './typecanalenum-update.component';
import { TypecanalenumDeleteDialogComponent } from './typecanalenum-delete-dialog.component';
import { typecanalenumRoute } from './typecanalenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(typecanalenumRoute)],
  declarations: [TypecanalenumComponent, TypecanalenumDetailComponent, TypecanalenumUpdateComponent, TypecanalenumDeleteDialogComponent],
  entryComponents: [TypecanalenumDeleteDialogComponent],
})
export class Transaction2TypecanalenumModule {}
