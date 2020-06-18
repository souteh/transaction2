import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStatutticketenum, Statutticketenum } from 'app/shared/model/statutticketenum.model';
import { StatutticketenumService } from './statutticketenum.service';

@Component({
  selector: 'jhi-statutticketenum-update',
  templateUrl: './statutticketenum-update.component.html',
})
export class StatutticketenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    valide: [null, [Validators.maxLength(254)]],
    annule: [null, [Validators.maxLength(254)]],
    paye: [null, [Validators.maxLength(254)]],
    rembourse: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected statutticketenumService: StatutticketenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statutticketenum }) => {
      this.updateForm(statutticketenum);
    });
  }

  updateForm(statutticketenum: IStatutticketenum): void {
    this.editForm.patchValue({
      id: statutticketenum.id,
      valide: statutticketenum.valide,
      annule: statutticketenum.annule,
      paye: statutticketenum.paye,
      rembourse: statutticketenum.rembourse,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const statutticketenum = this.createFromForm();
    if (statutticketenum.id !== undefined) {
      this.subscribeToSaveResponse(this.statutticketenumService.update(statutticketenum));
    } else {
      this.subscribeToSaveResponse(this.statutticketenumService.create(statutticketenum));
    }
  }

  private createFromForm(): IStatutticketenum {
    return {
      ...new Statutticketenum(),
      id: this.editForm.get(['id'])!.value,
      valide: this.editForm.get(['valide'])!.value,
      annule: this.editForm.get(['annule'])!.value,
      paye: this.editForm.get(['paye'])!.value,
      rembourse: this.editForm.get(['rembourse'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatutticketenum>>): void {
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
