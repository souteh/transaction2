import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatutticketenum } from 'app/shared/model/statutticketenum.model';
import { StatutticketenumService } from './statutticketenum.service';

@Component({
  templateUrl: './statutticketenum-delete-dialog.component.html',
})
export class StatutticketenumDeleteDialogComponent {
  statutticketenum?: IStatutticketenum;

  constructor(
    protected statutticketenumService: StatutticketenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.statutticketenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('statutticketenumListModification');
      this.activeModal.close();
    });
  }
}
