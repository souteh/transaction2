import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAssociation12, Association12 } from 'app/shared/model/association-12.model';
import { Association12Service } from './association-12.service';
import { IClientglentity } from 'app/shared/model/clientglentity.model';
import { ClientglentityService } from 'app/entities/clientglentity/clientglentity.service';

@Component({
  selector: 'jhi-association-12-update',
  templateUrl: './association-12-update.component.html',
})
export class Association12UpdateComponent implements OnInit {
  isSaving = false;
  clientglentities: IClientglentity[] = [];

  editForm = this.fb.group({
    id: [],
    identifiantconc: [],
    id: [],
  });

  constructor(
    protected association12Service: Association12Service,
    protected clientglentityService: ClientglentityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ association12 }) => {
      this.updateForm(association12);

      this.clientglentityService.query().subscribe((res: HttpResponse<IClientglentity[]>) => (this.clientglentities = res.body || []));
    });
  }

  updateForm(association12: IAssociation12): void {
    this.editForm.patchValue({
      id: association12.id,
      identifiantconc: association12.identifiantconc,
      id: association12.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const association12 = this.createFromForm();
    if (association12.id !== undefined) {
      this.subscribeToSaveResponse(this.association12Service.update(association12));
    } else {
      this.subscribeToSaveResponse(this.association12Service.create(association12));
    }
  }

  private createFromForm(): IAssociation12 {
    return {
      ...new Association12(),
      id: this.editForm.get(['id'])!.value,
      identifiantconc: this.editForm.get(['identifiantconc'])!.value,
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssociation12>>): void {
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

  trackById(index: number, item: IClientglentity): any {
    return item.id;
  }
}
