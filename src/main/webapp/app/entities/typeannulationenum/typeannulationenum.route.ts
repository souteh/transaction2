import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeannulationenum, Typeannulationenum } from 'app/shared/model/typeannulationenum.model';
import { TypeannulationenumService } from './typeannulationenum.service';
import { TypeannulationenumComponent } from './typeannulationenum.component';
import { TypeannulationenumDetailComponent } from './typeannulationenum-detail.component';
import { TypeannulationenumUpdateComponent } from './typeannulationenum-update.component';

@Injectable({ providedIn: 'root' })
export class TypeannulationenumResolve implements Resolve<ITypeannulationenum> {
  constructor(private service: TypeannulationenumService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeannulationenum> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeannulationenum: HttpResponse<Typeannulationenum>) => {
          if (typeannulationenum.body) {
            return of(typeannulationenum.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typeannulationenum());
  }
}

export const typeannulationenumRoute: Routes = [
  {
    path: '',
    component: TypeannulationenumComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.typeannulationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeannulationenumDetailComponent,
    resolve: {
      typeannulationenum: TypeannulationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeannulationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeannulationenumUpdateComponent,
    resolve: {
      typeannulationenum: TypeannulationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeannulationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeannulationenumUpdateComponent,
    resolve: {
      typeannulationenum: TypeannulationenumResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.typeannulationenum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
