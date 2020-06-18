import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssociation12 } from 'app/shared/model/association-12.model';
import { Association12Service } from './association-12.service';

@Component({
  templateUrl: './association-12-delete-dialog.component.html',
})
export class Association12DeleteDialogComponent {
  association12?: IAssociation12;

  constructor(
    protected association12Service: Association12Service,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.association12Service.delete(id).subscribe(() => {
      this.eventManager.broadcast('association12ListModification');
      this.activeModal.close();
    });
  }
}
