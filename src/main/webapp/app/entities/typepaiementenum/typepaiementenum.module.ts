import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { TypepaiementenumComponent } from './typepaiementenum.component';
import { TypepaiementenumDetailComponent } from './typepaiementenum-detail.component';
import { TypepaiementenumUpdateComponent } from './typepaiementenum-update.component';
import { TypepaiementenumDeleteDialogComponent } from './typepaiementenum-delete-dialog.component';
import { typepaiementenumRoute } from './typepaiementenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(typepaiementenumRoute)],
  declarations: [
    TypepaiementenumComponent,
    TypepaiementenumDetailComponent,
    TypepaiementenumUpdateComponent,
    TypepaiementenumDeleteDialogComponent,
  ],
  entryComponents: [TypepaiementenumDeleteDialogComponent],
})
export class Transaction2TypepaiementenumModule {}
