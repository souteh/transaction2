import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypecanalenum } from 'app/shared/model/typecanalenum.model';
import { TypecanalenumService } from './typecanalenum.service';

@Component({
  templateUrl: './typecanalenum-delete-dialog.component.html',
})
export class TypecanalenumDeleteDialogComponent {
  typecanalenum?: ITypecanalenum;

  constructor(
    protected typecanalenumService: TypecanalenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typecanalenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typecanalenumListModification');
      this.activeModal.close();
    });
  }
}
