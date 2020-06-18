import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeannulationenum } from 'app/shared/model/typeannulationenum.model';

@Component({
  selector: 'jhi-typeannulationenum-detail',
  templateUrl: './typeannulationenum-detail.component.html',
})
export class TypeannulationenumDetailComponent implements OnInit {
  typeannulationenum: ITypeannulationenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeannulationenum }) => (this.typeannulationenum = typeannulationenum));
  }

  previousState(): void {
    window.history.back();
  }
}
