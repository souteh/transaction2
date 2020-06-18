import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModeoperationenum } from 'app/shared/model/modeoperationenum.model';
import { ModeoperationenumService } from './modeoperationenum.service';

@Component({
  templateUrl: './modeoperationenum-delete-dialog.component.html',
})
export class ModeoperationenumDeleteDialogComponent {
  modeoperationenum?: IModeoperationenum;

  constructor(
    protected modeoperationenumService: ModeoperationenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modeoperationenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modeoperationenumListModification');
      this.activeModal.close();
    });
  }
}
