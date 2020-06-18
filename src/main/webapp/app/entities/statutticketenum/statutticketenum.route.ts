import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStatutticketenum, Statutticketenum } from 'app/shared/model/statutticketenum.model';
import { StatutticketenumService } from './statutticketenum.service';
import { StatutticketenumComponent } from './statutticketenum.component';
import { StatutticketenumDetailComponent } from './statutticketenum-detail.component';
import { StatutticketenumUpdateComponent } from './statutticketenum-update.component';

@Injectable({ providedIn: 'root' })
export class StatutticketenumResolve implements Resolve<IStatutticketenum> {
  constructor(private service: StatutticketenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatutticketenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((statutticketenum: HttpResponse<Statutticketenum>) => {
          if (statutticketenum.body) {
            return of(statutticketenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Statutticketenum());
  }
}

export const statutticketenumRoute: Routes = [
  {
    path: '',
    component: StatutticketenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.statutticketenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatutticketenumDetailComponent,
    resolve: {
      statutticketenum: StatutticketenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.statutticketenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StatutticketenumUpdateComponent,
    resolve: {
      statutticketenum: StatutticketenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.statutticketenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StatutticketenumUpdateComponent,
    resolve: {
      statutticketenum: StatutticketenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.statutticketenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
