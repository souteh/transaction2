import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICatonlinecanalenum, Catonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';
import { CatonlinecanalenumService } from './catonlinecanalenum.service';
import { CatonlinecanalenumComponent } from './catonlinecanalenum.component';
import { CatonlinecanalenumDetailComponent } from './catonlinecanalenum-detail.component';
import { CatonlinecanalenumUpdateComponent } from './catonlinecanalenum-update.component';

@Injectable({ providedIn: 'root' })
export class CatonlinecanalenumResolve implements Resolve<ICatonlinecanalenum> {
  constructor(private service: CatonlinecanalenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICatonlinecanalenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((catonlinecanalenum: HttpResponse<Catonlinecanalenum>) => {
          if (catonlinecanalenum.body) {
            return of(catonlinecanalenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Catonlinecanalenum());
  }
}

export const catonlinecanalenumRoute: Routes = [
  {
    path: '',
    component: CatonlinecanalenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.catonlinecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CatonlinecanalenumDetailComponent,
    resolve: {
      catonlinecanalenum: CatonlinecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.catonlinecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CatonlinecanalenumUpdateComponent,
    resolve: {
      catonlinecanalenum: CatonlinecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.catonlinecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CatonlinecanalenumUpdateComponent,
    resolve: {
      catonlinecanalenum: CatonlinecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.catonlinecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
