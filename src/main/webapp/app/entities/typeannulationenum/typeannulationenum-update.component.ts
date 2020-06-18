import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeannulationenum, Typeannulationenum } from 'app/shared/model/typeannulationenum.model';
import { TypeannulationenumService } from './typeannulationenum.service';

@Component({
  selector: 'jhi-typeannulationenum-update',
  templateUrl: './typeannulationenum-update.component.html',
})
export class TypeannulationenumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    client: [null, [Validators.maxLength(254)]],
    machine: [null, [Validators.maxLength(254)]],
    exceptionnelle: [null, [Validators.maxLength(254)]],
    systeme: [null, [Validators.maxLength(254)]],
  });

  constructor(
    protected typeannulationenumService: TypeannulationenumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeannulationenum }) => {
      this.updateForm(typeannulationenum);
    });
  }

  updateForm(typeannulationenum: ITypeannulationenum): void {
    this.editForm.patchValue({
      id: typeannulationenum.id,
      client: typeannulationenum.client,
      machine: typeannulationenum.machine,
      exceptionnelle: typeannulationenum.exceptionnelle,
      systeme: typeannulationenum.systeme,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeannulationenum = this.createFromForm();
    if (typeannulationenum.id !== undefined) {
      this.subscribeToSaveResponse(this.typeannulationenumService.update(typeannulationenum));
    } else {
      this.subscribeToSaveResponse(this.typeannulationenumService.create(typeannulationenum));
    }
  }

  private createFromForm(): ITypeannulationenum {
    return {
      ...new Typeannulationenum(),
      id: this.editForm.get(['id'])!.value,
      client: this.editForm.get(['client'])!.value,
      machine: this.editForm.get(['machine'])!.value,
      exceptionnelle: this.editForm.get(['exceptionnelle'])!.value,
      systeme: this.editForm.get(['systeme'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeannulationenum>>): void {
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
