import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';
import { CatonlinecanalenumService } from './catonlinecanalenum.service';

@Component({
  templateUrl: './catonlinecanalenum-delete-dialog.component.html',
})
export class CatonlinecanalenumDeleteDialogComponent {
  catonlinecanalenum?: ICatonlinecanalenum;

  constructor(
    protected catonlinecanalenumService: CatonlinecanalenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.catonlinecanalenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('catonlinecanalenumListModification');
      this.activeModal.close();
    });
  }
}
