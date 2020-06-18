import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { ClientglentityComponent } from './clientglentity.component';
import { ClientglentityDetailComponent } from './clientglentity-detail.component';
import { ClientglentityUpdateComponent } from './clientglentity-update.component';
import { ClientglentityDeleteDialogComponent } from './clientglentity-delete-dialog.component';
import { clientglentityRoute } from './clientglentity.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(clientglentityRoute)],
  declarations: [
    ClientglentityComponent,
    ClientglentityDetailComponent,
    ClientglentityUpdateComponent,
    ClientglentityDeleteDialogComponent,
  ],
  entryComponents: [ClientglentityDeleteDialogComponent],
})
export class Transaction2ClientglentityModule {}
