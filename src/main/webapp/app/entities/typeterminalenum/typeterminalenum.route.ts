import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeterminalenum, Typeterminalenum } from 'app/shared/model/typeterminalenum.model';
import { TypeterminalenumService } from './typeterminalenum.service';
import { TypeterminalenumComponent } from './typeterminalenum.component';
import { TypeterminalenumDetailComponent } from './typeterminalenum-detail.component';
import { TypeterminalenumUpdateComponent } from './typeterminalenum-update.component';

@Injectable({ providedIn: 'root' })
export class TypeterminalenumResolve implements Resolve<ITypeterminalenum> {
  constructor(private service: TypeterminalenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeterminalenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeterminalenum: HttpResponse<Typeterminalenum>) => {
          if (typeterminalenum.body) {
            return of(typeterminalenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typeterminalenum());
  }
}

export const typeterminalenumRoute: Routes = [
  {
    path: '',
    component: TypeterminalenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.typeterminalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeterminalenumDetailComponent,
    resolve: {
      typeterminalenum: TypeterminalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeterminalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeterminalenumUpdateComponent,
    resolve: {
      typeterminalenum: TypeterminalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeterminalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeterminalenumUpdateComponent,
    resolve: {
      typeterminalenum: TypeterminalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeterminalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
