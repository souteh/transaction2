import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypepaiementenum } from 'app/shared/model/typepaiementenum.model';

@Component({
  selector: 'jhi-typepaiementenum-detail',
  templateUrl: './typepaiementenum-detail.component.html',
})
export class TypepaiementenumDetailComponent implements OnInit {
  typepaiementenum: ITypepaiementenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typepaiementenum }) => (this.typepaiementenum = typepaiementenum));
  }

  previousState(): void {
    window.history.back();
  }
}
