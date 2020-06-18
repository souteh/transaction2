import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeterminalenum } from 'app/shared/model/typeterminalenum.model';
import { TypeterminalenumService } from './typeterminalenum.service';

@Component({
  templateUrl: './typeterminalenum-delete-dialog.component.html',
})
export class TypeterminalenumDeleteDialogComponent {
  typeterminalenum?: ITypeterminalenum;

  constructor(
    protected typeterminalenumService: TypeterminalenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeterminalenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeterminalenumListModification');
      this.activeModal.close();
    });
  }
}
