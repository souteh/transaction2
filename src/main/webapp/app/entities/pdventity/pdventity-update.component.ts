import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPdventity, Pdventity } from 'app/shared/model/pdventity.model';
import { PdventityService } from './pdventity.service';

@Component({
  selector: 'jhi-pdventity-update',
  templateUrl: './pdventity-update.component.html',
})
export class PdventityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idpdv: [null, [Validators.required]],
    referencepdv: [null, [Validators.maxLength(254)]],
  });

  constructor(protected pdventityService: PdventityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pdventity }) => {
      this.updateForm(pdventity);
    });
  }

  updateForm(pdventity: IPdventity): void {
    this.editForm.patchValue({
      id: pdventity.id,
      idpdv: pdventity.idpdv,
      referencepdv: pdventity.referencepdv,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pdventity = this.createFromForm();
    if (pdventity.id !== undefined) {
      this.subscribeToSaveResponse(this.pdventityService.update(pdventity));
    } else {
      this.subscribeToSaveResponse(this.pdventityService.create(pdventity));
    }
  }

  private createFromForm(): IPdventity {
    return {
      ...new Pdventity(),
      id: this.editForm.get(['id'])!.value,
      idpdv: this.editForm.get(['idpdv'])!.value,
      referencepdv: this.editForm.get(['referencepdv'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdventity>>): void {
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
