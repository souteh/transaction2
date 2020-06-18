import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypecanalenum } from 'app/shared/model/typecanalenum.model';

@Component({
  selector: 'jhi-typecanalenum-detail',
  templateUrl: './typecanalenum-detail.component.html',
})
export class TypecanalenumDetailComponent implements OnInit {
  typecanalenum: ITypecanalenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typecanalenum }) => (this.typecanalenum = typecanalenum));
  }

  previousState(): void {
    window.history.back();
  }
}
