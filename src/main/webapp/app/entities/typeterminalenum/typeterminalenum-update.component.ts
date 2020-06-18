import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeterminalenum, Typeterminalenum } from 'app/shared/model/typeterminalenum.model';
import { TypeterminalenumService } from './typeterminalenum.service';

@Component({
  selector: 'jhi-typeterminalenum-update',
  templateUrl: './typeterminalenum-update.component.html',
})
export class TypeterminalenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    t2020: [null, [Validators.maxLength(254)]],
    t2030: [null, [Validators.maxLength(254)]],
    b2062: [null, [Validators.maxLength(254)]],
    pda: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected typeterminalenumService: TypeterminalenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeterminalenum }) => {
      this.updateForm(typeterminalenum);
    });
  }

  updateForm(typeterminalenum: ITypeterminalenum): void {
    this.editForm.patchValue({
      id: typeterminalenum.id,
      t2020: typeterminalenum.t2020,
      t2030: typeterminalenum.t2030,
      b2062: typeterminalenum.b2062,
      pda: typeterminalenum.pda,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeterminalenum = this.createFromForm();
    if (typeterminalenum.id !== undefined) {
      this.subscribeToSaveResponse(this.typeterminalenumService.update(typeterminalenum));
    } else {
      this.subscribeToSaveResponse(this.typeterminalenumService.create(typeterminalenum));
    }
  }

  private createFromForm(): ITypeterminalenum {
    return {
      ...new Typeterminalenum(),
      id: this.editForm.get(['id'])!.value,
      t2020: this.editForm.get(['t2020'])!.value,
      t2030: this.editForm.get(['t2030'])!.value,
      b2062: this.editForm.get(['b2062'])!.value,
      pda: this.editForm.get(['pda'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeterminalenum>>): void {
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
