import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAssociation12, Association12 } from 'app/shared/model/association-12.model';
import { Association12Service } from './association-12.service';
import { Association12Component } from './association-12.component';
import { Association12DetailComponent } from './association-12-detail.component';
import { Association12UpdateComponent } from './association-12-update.component';

@Injectable({ providedIn: 'root' })
export class Association12Resolve implements Resolve<IAssociation12> {
  constructor(private service: Association12Service, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAssociation12> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((association12: HttpResponse<Association12>) => {
          if (association12.body) {
            return of(association12.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Association12());
  }
}

export const association12Route: Routes = [
  {
    path: '',
    component: Association12Component,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.association12.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Association12DetailComponent,
    resolve: {
      association12: Association12Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.association12.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Association12UpdateComponent,
    resolve: {
      association12: Association12Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.association12.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Association12UpdateComponent,
    resolve: {
      association12: Association12Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.association12.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
