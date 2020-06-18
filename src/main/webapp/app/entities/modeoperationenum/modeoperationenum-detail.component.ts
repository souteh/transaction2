import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModeoperationenum } from 'app/shared/model/modeoperationenum.model';

@Component({
  selector: 'jhi-modeoperationenum-detail',
  templateUrl: './modeoperationenum-detail.component.html',
})
export class ModeoperationenumDetailComponent implements OnInit {
  modeoperationenum: IModeoperationenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeoperationenum }) => (this.modeoperationenum = modeoperationenum));
  }

  previousState(): void {
    window.history.back();
  }
}
