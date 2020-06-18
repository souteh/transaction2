import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeterminalenum } from 'app/shared/model/typeterminalenum.model';

@Component({
  selector: 'jhi-typeterminalenum-detail',
  templateUrl: './typeterminalenum-detail.component.html',
})
export class TypeterminalenumDetailComponent implements OnInit {
  typeterminalenum: ITypeterminalenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeterminalenum }) => (this.typeterminalenum = typeterminalenum));
  }

  previousState(): void {
    window.history.back();
  }
}
