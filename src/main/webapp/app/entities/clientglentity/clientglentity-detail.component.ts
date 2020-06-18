import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClientglentity } from 'app/shared/model/clientglentity.model';

@Component({
  selector: 'jhi-clientglentity-detail',
  templateUrl: './clientglentity-detail.component.html',
})
export class ClientglentityDetailComponent implements OnInit {
  clientglentity: IClientglentity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientglentity }) => (this.clientglentity = clientglentity));
  }

  previousState(): void {
    window.history.back();
  }
}
