import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClientglentity } from 'app/shared/model/clientglentity.model';
import { ClientglentityService } from './clientglentity.service';

@Component({
  templateUrl: './clientglentity-delete-dialog.component.html',
})
export class ClientglentityDeleteDialogComponent {
  clientglentity?: IClientglentity;

  constructor(
    protected clientglentityService: ClientglentityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.clientglentityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('clientglentityListModification');
      this.activeModal.close();
    });
  }
}
