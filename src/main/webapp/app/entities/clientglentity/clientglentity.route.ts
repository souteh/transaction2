import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClientglentity, Clientglentity } from 'app/shared/model/clientglentity.model';
import { ClientglentityService } from './clientglentity.service';
import { ClientglentityComponent } from './clientglentity.component';
import { ClientglentityDetailComponent } from './clientglentity-detail.component';
import { ClientglentityUpdateComponent } from './clientglentity-update.component';

@Injectable({ providedIn: 'root' })
export class ClientglentityResolve implements Resolve<IClientglentity> {
  constructor(private service: ClientglentityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClientglentity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((clientglentity: HttpResponse<Clientglentity>) => {
          if (clientglentity.body) {
            return of(clientglentity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Clientglentity());
  }
}

export const clientglentityRoute: Routes = [
  {
    path: '',
    component: ClientglentityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'transaction2App.clientglentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientglentityDetailComponent,
    resolve: {
      clientglentity: ClientglentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.clientglentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientglentityUpdateComponent,
    resolve: {
      clientglentity: ClientglentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.clientglentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientglentityUpdateComponent,
    resolve: {
      clientglentity: ClientglentityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'transaction2App.clientglentity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
