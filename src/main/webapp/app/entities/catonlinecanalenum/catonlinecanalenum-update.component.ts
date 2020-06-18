import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICatonlinecanalenum, Catonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';
import { CatonlinecanalenumService } from './catonlinecanalenum.service';

@Component({
  selector: 'jhi-catonlinecanalenum-update',
  templateUrl: './catonlinecanalenum-update.component.html',
})
export class CatonlinecanalenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    pel: [null, [Validators.maxLength(254)]],
    ptel: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected catonlinecanalenumService: CatonlinecanalenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catonlinecanalenum }) => {
      this.updateForm(catonlinecanalenum);
    });
  }

  updateForm(catonlinecanalenum: ICatonlinecanalenum): void {
    this.editForm.patchValue({
      id: catonlinecanalenum.id,
      pel: catonlinecanalenum.pel,
      ptel: catonlinecanalenum.ptel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const catonlinecanalenum = this.createFromForm();
    if (catonlinecanalenum.id !== undefined) {
      this.subscribeToSaveResponse(this.catonlinecanalenumService.update(catonlinecanalenum));
    } else {
      this.subscribeToSaveResponse(this.catonlinecanalenumService.create(catonlinecanalenum));
    }
  }

  private createFromForm(): ICatonlinecanalenum {
    return {
      ...new Catonlinecanalenum(),
      id: this.editForm.get(['id'])!.value,
      pel: this.editForm.get(['pel'])!.value,
      ptel: this.editForm.get(['ptel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICatonlinecanalenum>>): void {
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
