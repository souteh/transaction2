import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPdventity } from 'app/shared/model/pdventity.model';

@Component({
  selector: 'jhi-pdventity-detail',
  templateUrl: './pdventity-detail.component.html',
})
export class PdventityDetailComponent implements OnInit {
  pdventity: IPdventity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pdventity }) => (this.pdventity = pdventity));
  }

  previousState(): void {
    window.history.back();
  }
}
