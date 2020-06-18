import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormulationentity, Formulationentity } from 'app/shared/model/formulationentity.model';
import { FormulationentityService } from './formulationentity.service';

@Component({
  selector: 'jhi-formulationentity-update',
  templateUrl: './formulationentity-update.component.html',
})
export class FormulationentityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    identifiantconc: [null, [Validators.required]],
    idformulation: [],
    codeproduit: [null, [Validators.maxLength(254)]],
    formcomplete: [],
    designation: [null, [Validators.maxLength(254)]],
    misecomb: [null, [Validators.maxLength(254)]],
    misetotale: [],
    numform: [],
  });

  constructor(
    protected formulationentityService: FormulationentityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formulationentity }) => {
      this.updateForm(formulationentity);
    });
  }

  updateForm(formulationentity: IFormulationentity): void {
    this.editForm.patchValue({
      id: formulationentity.id,
      identifiantconc: formulationentity.identifiantconc,
      idformulation: formulationentity.idformulation,
      codeproduit: formulationentity.codeproduit,
      formcomplete: formulationentity.formcomplete,
      designation: formulationentity.designation,
      misecomb: formulationentity.misecomb,
      misetotale: formulationentity.misetotale,
      numform: formulationentity.numform,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formulationentity = this.createFromForm();
    if (formulationentity.id !== undefined) {
      this.subscribeToSaveResponse(this.formulationentityService.update(formulationentity));
    } else {
      this.subscribeToSaveResponse(this.formulationentityService.create(formulationentity));
    }
  }

  private createFromForm(): IFormulationentity {
    return {
      ...new Formulationentity(),
      id: this.editForm.get(['id'])!.value,
      identifiantconc: this.editForm.get(['identifiantconc'])!.value,
      idformulation: this.editForm.get(['idformulation'])!.value,
      codeproduit: this.editForm.get(['codeproduit'])!.value,
      formcomplete: this.editForm.get(['formcomplete'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      misecomb: this.editForm.get(['misecomb'])!.value,
      misetotale: this.editForm.get(['misetotale'])!.value,
      numform: this.editForm.get(['numform'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormulationentity>>): void {
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
