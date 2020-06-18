import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssociation12 } from 'app/shared/model/association-12.model';

@Component({
  selector: 'jhi-association-12-detail',
  templateUrl: './association-12-detail.component.html',
})
export class Association12DetailComponent implements OnInit {
  association12: IAssociation12 | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ association12 }) => (this.association12 = association12));
  }

  previousState(): void {
    window.history.back();
  }
}
