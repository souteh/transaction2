import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModeoperationenum, Modeoperationenum } from 'app/shared/model/modeoperationenum.model';
import { ModeoperationenumService } from './modeoperationenum.service';
import { ModeoperationenumComponent } from './modeoperationenum.component';
import { ModeoperationenumDetailComponent } from './modeoperationenum-detail.component';
import { ModeoperationenumUpdateComponent } from './modeoperationenum-update.component';

@Injectable({ providedIn: 'root' })
export class ModeoperationenumResolve implements Resolve<IModeoperationenum> {
  constructor(private service: ModeoperationenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModeoperationenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modeoperationenum: HttpResponse<Modeoperationenum>) => {
          if (modeoperationenum.body) {
            return of(modeoperationenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Modeoperationenum());
  }
}

export const modeoperationenumRoute: Routes = [
  {
    path: '',
    component: ModeoperationenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.modeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModeoperationenumDetailComponent,
    resolve: {
      modeoperationenum: ModeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.modeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModeoperationenumUpdateComponent,
    resolve: {
      modeoperationenum: ModeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.modeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModeoperationenumUpdateComponent,
    resolve: {
      modeoperationenum: ModeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.modeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
