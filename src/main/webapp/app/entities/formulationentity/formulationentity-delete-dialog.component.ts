import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormulationentity } from 'app/shared/model/formulationentity.model';
import { FormulationentityService } from './formulationentity.service';

@Component({
  templateUrl: './formulationentity-delete-dialog.component.html',
})
export class FormulationentityDeleteDialogComponent {
  formulationentity?: IFormulationentity;

  constructor(
    protected formulationentityService: FormulationentityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formulationentityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formulationentityListModification');
      this.activeModal.close();
    });
  }
}
