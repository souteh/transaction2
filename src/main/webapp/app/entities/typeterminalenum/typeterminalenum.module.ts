import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Transaction2SharedModule } from 'app/shared/shared.module';
import { TypeterminalenumComponent } from './typeterminalenum.component';
import { TypeterminalenumDetailComponent } from './typeterminalenum-detail.component';
import { TypeterminalenumUpdateComponent } from './typeterminalenum-update.component';
import { TypeterminalenumDeleteDialogComponent } from './typeterminalenum-delete-dialog.component';
import { typeterminalenumRoute } from './typeterminalenum.route';

@NgModule({
  imports: [Transaction2SharedModule, RouterModule.forChild(typeterminalenumRoute)],
  declarations: [
    TypeterminalenumComponent,
    TypeterminalenumDetailComponent,
    TypeterminalenumUpdateComponent,
    TypeterminalenumDeleteDialogComponent,
  ],
  entryComponents: [TypeterminalenumDeleteDialogComponent],
})
export class Transaction2TypeterminalenumModule {}
