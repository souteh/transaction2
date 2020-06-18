import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeoperationenum } from 'app/shared/model/typeoperationenum.model';

@Component({
  selector: 'jhi-typeoperationenum-detail',
  templateUrl: './typeoperationenum-detail.component.html',
})
export class TypeoperationenumDetailComponent implements OnInit {
  typeoperationenum: ITypeoperationenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeoperationenum }) => (this.typeoperationenum = typeoperationenum));
  }

  previousState(): void {
    window.history.back();
  }
}
