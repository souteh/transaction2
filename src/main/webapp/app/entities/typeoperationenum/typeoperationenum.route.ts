import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeoperationenum, Typeoperationenum } from 'app/shared/model/typeoperationenum.model';
import { TypeoperationenumService } from './typeoperationenum.service';
import { TypeoperationenumComponent } from './typeoperationenum.component';
import { TypeoperationenumDetailComponent } from './typeoperationenum-detail.component';
import { TypeoperationenumUpdateComponent } from './typeoperationenum-update.component';

@Injectable({ providedIn: 'root' })
export class TypeoperationenumResolve implements Resolve<ITypeoperationenum> {
  constructor(private service: TypeoperationenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeoperationenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeoperationenum: HttpResponse<Typeoperationenum>) => {
          if (typeoperationenum.body) {
            return of(typeoperationenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typeoperationenum());
  }
}

export const typeoperationenumRoute: Routes = [
  {
    path: '',
    component: TypeoperationenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.typeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeoperationenumDetailComponent,
    resolve: {
      typeoperationenum: TypeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeoperationenumUpdateComponent,
    resolve: {
      typeoperationenum: TypeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeoperationenumUpdateComponent,
    resolve: {
      typeoperationenum: TypeoperationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeoperationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
