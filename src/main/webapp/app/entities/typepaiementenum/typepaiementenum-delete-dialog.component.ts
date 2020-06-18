import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypepaiementenum } from 'app/shared/model/typepaiementenum.model';
import { TypepaiementenumService } from './typepaiementenum.service';

@Component({
  templateUrl: './typepaiementenum-delete-dialog.component.html',
})
export class TypepaiementenumDeleteDialogComponent {
  typepaiementenum?: ITypepaiementenum;

  constructor(
    protected typepaiementenumService: TypepaiementenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typepaiementenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typepaiementenumListModification');
      this.activeModal.close();
    });
  }
}
