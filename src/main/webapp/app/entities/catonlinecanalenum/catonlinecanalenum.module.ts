import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { CatonlinecanalenumComponent } from './catonlinecanalenum.component';
import { CatonlinecanalenumDetailComponent } from './catonlinecanalenum-detail.component';
import { CatonlinecanalenumUpdateComponent } from './catonlinecanalenum-update.component';
import { CatonlinecanalenumDeleteDialogComponent } from './catonlinecanalenum-delete-dialog.component';
import { catonlinecanalenumRoute } from './catonlinecanalenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(catonlinecanalenumRoute)],
  declarations: [
    CatonlinecanalenumComponent,
    CatonlinecanalenumDetailComponent,
    CatonlinecanalenumUpdateComponent,
    CatonlinecanalenumDeleteDialogComponent,
  ],
  entryComponents: [CatonlinecanalenumDeleteDialogComponent],
})
export class Transaction2CatonlinecanalenumModule {}
