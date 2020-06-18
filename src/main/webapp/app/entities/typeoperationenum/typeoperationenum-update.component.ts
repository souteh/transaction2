import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeoperationenum, Typeoperationenum } from 'app/shared/model/typeoperationenum.model';
import { TypeoperationenumService } from './typeoperationenum.service';

@Component({
  selector: 'jhi-typeoperationenum-update',
  templateUrl: './typeoperationenum-update.component.html',
})
export class TypeoperationenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    pari: [null, [Validators.maxLength(254)]],
    annulation: [null, [Validators.maxLength(254)]],
    paiement: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected typeoperationenumService: TypeoperationenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeoperationenum }) => {
      this.updateForm(typeoperationenum);
    });
  }

  updateForm(typeoperationenum: ITypeoperationenum): void {
    this.editForm.patchValue({
      id: typeoperationenum.id,
      pari: typeoperationenum.pari,
      annulation: typeoperationenum.annulation,
      paiement: typeoperationenum.paiement,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeoperationenum = this.createFromForm();
    if (typeoperationenum.id !== undefined) {
      this.subscribeToSaveResponse(this.typeoperationenumService.update(typeoperationenum));
    } else {
      this.subscribeToSaveResponse(this.typeoperationenumService.create(typeoperationenum));
    }
  }

  private createFromForm(): ITypeoperationenum {
    return {
      ...new Typeoperationenum(),
      id: this.editForm.get(['id'])!.value,
      pari: this.editForm.get(['pari'])!.value,
      annulation: this.editForm.get(['annulation'])!.value,
      paiement: this.editForm.get(['paiement'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeoperationenum>>): void {
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
