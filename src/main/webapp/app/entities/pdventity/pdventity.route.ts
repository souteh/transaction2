import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPdventity, Pdventity } from 'app/shared/model/pdventity.model';
import { PdventityService } from './pdventity.service';
import { PdventityComponent } from './pdventity.component';
import { PdventityDetailComponent } from './pdventity-detail.component';
import { PdventityUpdateComponent } from './pdventity-update.component';

@Injectable({ providedIn: 'root' })
export class PdventityResolve implements Resolve<IPdventity> {
  constructor(private service: PdventityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPdventity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pdventity: HttpResponse<Pdventity>) => {
          if (pdventity.body) {
            return of(pdventity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Pdventity());
  }
}

export const pdventityRoute: Routes = [
  {
    path: '',
    component: PdventityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.pdventity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PdventityDetailComponent,
    resolve: {
      pdventity: PdventityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.pdventity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PdventityUpdateComponent,
    resolve: {
      pdventity: PdventityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.pdventity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PdventityUpdateComponent,
    resolve: {
      pdventity: PdventityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.pdventity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
