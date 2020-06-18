import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';

@Component({
  selector: 'jhi-catonlinecanalenum-detail',
  templateUrl: './catonlinecanalenum-detail.component.html',
})
export class CatonlinecanalenumDetailComponent implements OnInit {
  catonlinecanalenum: ICatonlinecanalenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catonlinecanalenum }) => (this.catonlinecanalenum = catonlinecanalenum));
  }

  previousState(): void {
    window.history.back();
  }
}
