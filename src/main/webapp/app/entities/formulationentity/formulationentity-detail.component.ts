import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormulationentity } from 'app/shared/model/formulationentity.model';

@Component({
  selector: 'jhi-formulationentity-detail',
  templateUrl: './formulationentity-detail.component.html',
})
export class FormulationentityDetailComponent implements OnInit {
  formulationentity: IFormulationentity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formulationentity }) => (this.formulationentity = formulationentity));
  }

  previousState(): void {
    window.history.back();
  }
}
