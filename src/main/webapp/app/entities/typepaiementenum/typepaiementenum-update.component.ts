import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypepaiementenum, Typepaiementenum } from 'app/shared/model/typepaiementenum.model';
import { TypepaiementenumService } from './typepaiementenum.service';

@Component({
  selector: 'jhi-typepaiementenum-update',
  templateUrl: './typepaiementenum-update.component.html',
})
export class TypepaiementenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    total: [null, [Validators.maxLength(254)]],
    avance: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected typepaiementenumService: TypepaiementenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typepaiementenum }) => {
      this.updateForm(typepaiementenum);
    });
  }

  updateForm(typepaiementenum: ITypepaiementenum): void {
    this.editForm.patchValue({
      id: typepaiementenum.id,
      total: typepaiementenum.total,
      avance: typepaiementenum.avance,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typepaiementenum = this.createFromForm();
    if (typepaiementenum.id !== undefined) {
      this.subscribeToSaveResponse(this.typepaiementenumService.update(typepaiementenum));
    } else {
      this.subscribeToSaveResponse(this.typepaiementenumService.create(typepaiementenum));
    }
  }

  private createFromForm(): ITypepaiementenum {
    return {
      ...new Typepaiementenum(),
      id: this.editForm.get(['id'])!.value,
      total: this.editForm.get(['total'])!.value,
      avance: this.editForm.get(['avance'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypepaiementenum>>): void {
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
