import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypecanalenum, Typecanalenum } from 'app/shared/model/typecanalenum.model';
import { TypecanalenumService } from './typecanalenum.service';

@Component({
  selector: 'jhi-typecanalenum-update',
  templateUrl: './typecanalenum-update.component.html',
})
export class TypecanalenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    terminal: [null, [Validators.maxLength(254)]],
    online: [null, [Validators.maxLength(254)]],
  });

  constructor(protected typecanalenumService: TypecanalenumService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typecanalenum }) => {
      this.updateForm(typecanalenum);
    });
  }

  updateForm(typecanalenum: ITypecanalenum): void {
    this.editForm.patchValue({
      id: typecanalenum.id,
      terminal: typecanalenum.terminal,
      online: typecanalenum.online,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typecanalenum = this.createFromForm();
    if (typecanalenum.id !== undefined) {
      this.subscribeToSaveResponse(this.typecanalenumService.update(typecanalenum));
    } else {
      this.subscribeToSaveResponse(this.typecanalenumService.create(typecanalenum));
    }
  }

  private createFromForm(): ITypecanalenum {
    return {
      ...new Typecanalenum(),
      id: this.editForm.get(['id'])!.value,
      terminal: this.editForm.get(['terminal'])!.value,
      online: this.editForm.get(['online'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypecanalenum>>): void {
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
