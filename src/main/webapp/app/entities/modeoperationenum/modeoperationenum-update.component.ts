import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModeoperationenum, Modeoperationenum } from 'app/shared/model/modeoperationenum.model';
import { ModeoperationenumService } from './modeoperationenum.service';

@Component({
  selector: 'jhi-modeoperationenum-update',
  templateUrl: './modeoperationenum-update.component.html',
})
export class ModeoperationenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    automatique: [null, [Validators.maxLength(254)]],
    manuel: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected modeoperationenumService: ModeoperationenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeoperationenum }) => {
      this.updateForm(modeoperationenum);
    });
  }

  updateForm(modeoperationenum: IModeoperationenum): void {
    this.editForm.patchValue({
      id: modeoperationenum.id,
      automatique: modeoperationenum.automatique,
      manuel: modeoperationenum.manuel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modeoperationenum = this.createFromForm();
    if (modeoperationenum.id !== undefined) {
      this.subscribeToSaveResponse(this.modeoperationenumService.update(modeoperationenum));
    } else {
      this.subscribeToSaveResponse(this.modeoperationenumService.create(modeoperationenum));
    }
  }

  private createFromForm(): IModeoperationenum {
    return {
      ...new Modeoperationenum(),
      id: this.editForm.get(['id'])!.value,
      automatique: this.editForm.get(['automatique'])!.value,
      manuel: this.editForm.get(['manuel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModeoperationenum>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
