import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClientglentity, Clientglentity } from 'app/shared/model/clientglentity.model';
import { ClientglentityService } from './clientglentity.service';

@Component({
  selector: 'jhi-clientglentity-update',
  templateUrl: './clientglentity-update.component.html',
})
export class ClientglentityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.maxLength(254)]],
    prenom: [null, [Validators.maxLength(254)]],
    cin: [null, [Validators.maxLength(254)]],
    telephone: [null, [Validators.maxLength(254)]],
    commentaire: [null, [Validators.maxLength(254)]],
  });

  constructor(protected clientglentityService: ClientglentityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientglentity }) => {
      this.updateForm(clientglentity);
    });
  }

  updateForm(clientglentity: IClientglentity): void {
    this.editForm.patchValue({
      id: clientglentity.id,
      nom: clientglentity.nom,
      prenom: clientglentity.prenom,
      cin: clientglentity.cin,
      telephone: clientglentity.telephone,
      commentaire: clientglentity.commentaire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientglentity = this.createFromForm();
    if (clientglentity.id !== undefined) {
      this.subscribeToSaveResponse(this.clientglentityService.update(clientglentity));
    } else {
      this.subscribeToSaveResponse(this.clientglentityService.create(clientglentity));
    }
  }

  private createFromForm(): IClientglentity {
    return {
      ...new Clientglentity(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      cin: this.editForm.get(['cin'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      commentaire: this.editForm.get(['commentaire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientglentity>>): void {
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
