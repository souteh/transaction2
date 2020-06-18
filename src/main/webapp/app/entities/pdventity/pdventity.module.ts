import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { PdventityComponent } from './pdventity.component';
import { PdventityDetailComponent } from './pdventity-detail.component';
import { PdventityUpdateComponent } from './pdventity-update.component';
import { PdventityDeleteDialogComponent } from './pdventity-delete-dialog.component';
import { pdventityRoute } from './pdventity.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(pdventityRoute)],
  declarations: [PdventityComponent, PdventityDetailComponent, PdventityUpdateComponent, PdventityDeleteDialogComponent],
  entryComponents: [PdventityDeleteDialogComponent],
})
export class Transaction2PdventityModule {}
