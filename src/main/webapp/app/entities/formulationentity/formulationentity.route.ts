import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormulationentity, Formulationentity } from 'app/shared/model/formulationentity.model';
import { FormulationentityService } from './formulationentity.service';
import { FormulationentityComponent } from './formulationentity.component';
import { FormulationentityDetailComponent } from './formulationentity-detail.component';
import { FormulationentityUpdateComponent } from './formulationentity-update.component';

@Injectable({ providedIn: 'root' })
export class FormulationentityResolve implements Resolve<IFormulationentity> {
  constructor(private service: FormulationentityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormulationentity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formulationentity: HttpResponse<Formulationentity>) => {
          if (formulationentity.body) {
            return of(formulationentity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Formulationentity());
  }
}

export const formulationentityRoute: Routes = [
  {
    path: '',
    component: FormulationentityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.formulationentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormulationentityDetailComponent,
    resolve: {
      formulationentity: FormulationentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.formulationentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormulationentityUpdateComponent,
    resolve: {
      formulationentity: FormulationentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.formulationentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormulationentityUpdateComponent,
    resolve: {
      formulationentity: FormulationentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.formulationentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
