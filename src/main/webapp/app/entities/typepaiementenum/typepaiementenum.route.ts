import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypepaiementenum, Typepaiementenum } from 'app/shared/model/typepaiementenum.model';
import { TypepaiementenumService } from './typepaiementenum.service';
import { TypepaiementenumComponent } from './typepaiementenum.component';
import { TypepaiementenumDetailComponent } from './typepaiementenum-detail.component';
import { TypepaiementenumUpdateComponent } from './typepaiementenum-update.component';

@Injectable({ providedIn: 'root' })
export class TypepaiementenumResolve implements Resolve<ITypepaiementenum> {
  constructor(private service: TypepaiementenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypepaiementenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typepaiementenum: HttpResponse<Typepaiementenum>) => {
          if (typepaiementenum.body) {
            return of(typepaiementenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typepaiementenum());
  }
}

export const typepaiementenumRoute: Routes = [
  {
    path: '',
    component: TypepaiementenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.typepaiementenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypepaiementenumDetailComponent,
    resolve: {
      typepaiementenum: TypepaiementenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typepaiementenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypepaiementenumUpdateComponent,
    resolve: {
      typepaiementenum: TypepaiementenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typepaiementenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypepaiementenumUpdateComponent,
    resolve: {
      typepaiementenum: TypepaiementenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typepaiementenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
