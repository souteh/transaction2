import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatutticketenum } from 'app/shared/model/statutticketenum.model';

@Component({
  selector: 'jhi-statutticketenum-detail',
  templateUrl: './statutticketenum-detail.component.html',
})
export class StatutticketenumDetailComponent implements OnInit {
  statutticketenum: IStatutticketenum | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statutticketenum }) => (this.statutticketenum = statutticketenum));
  }

  previousState(): void {
    window.history.back();
  }
}
