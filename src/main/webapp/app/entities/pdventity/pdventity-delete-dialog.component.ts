import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPdventity } from 'app/shared/model/pdventity.model';
import { PdventityService } from './pdventity.service';

@Component({
  templateUrl: './pdventity-delete-dialog.component.html',
})
export class PdventityDeleteDialogComponent {
  pdventity?: IPdventity;

  constructor(protected pdventityService: PdventityService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pdventityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pdventityListModification');
      this.activeModal.close();
    });
  }
}
