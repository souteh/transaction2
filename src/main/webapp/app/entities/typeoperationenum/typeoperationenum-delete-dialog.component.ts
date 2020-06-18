import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeoperationenum } from 'app/shared/model/typeoperationenum.model';
import { TypeoperationenumService } from './typeoperationenum.service';

@Component({
  templateUrl: './typeoperationenum-delete-dialog.component.html',
})
export class TypeoperationenumDeleteDialogComponent {
  typeoperationenum?: ITypeoperationenum;

  constructor(
    protected typeoperationenumService: TypeoperationenumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeoperationenumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeoperationenumListModification');
      this.activeModal.close();
    });
  }
}
