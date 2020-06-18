import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeannulationenum } from 'app/shared/model/typeannulationenum.model';
import { TypeannulationenumService } from './typeannulationenum.service';

@Component({
  templateUrl: './typeannulationenum-delete-dialog.component.html',
})
export class TypeannulationenumDeleteDialogComponent {
  typeannulationenum?: ITypeannulationenum;

  constructor(
    protected typeannulationenumService: TypeannulationenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeannulationenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeannulationenumListModification');
      this.activeModal.close();
    });
  }
}
