import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypecanalenum, Typecanalenum } from 'app/shared/model/typecanalenum.model';
import { TypecanalenumService } from './typecanalenum.service';
import { TypecanalenumComponent } from './typecanalenum.component';
import { TypecanalenumDetailComponent } from './typecanalenum-detail.component';
import { TypecanalenumUpdateComponent } from './typecanalenum-update.component';

@Injectable({ providedIn: 'root' })
export class TypecanalenumResolve implements Resolve<ITypecanalenum> {
  constructor(private service: TypecanalenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypecanalenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typecanalenum: HttpResponse<Typecanalenum>) => {
          if (typecanalenum.body) {
            return of(typecanalenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typecanalenum());
  }
}

export const typecanalenumRoute: Routes = [
  {
    path: '',
    component: TypecanalenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.typecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypecanalenumDetailComponent,
    resolve: {
      typecanalenum: TypecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypecanalenumUpdateComponent,
    resolve: {
      typecanalenum: TypecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypecanalenumUpdateComponent,
    resolve: {
      typecanalenum: TypecanalenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typecanalenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
