import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssociation12 } from 'app/shared/model/association-12.model';

type EntityResponseType = HttpResponse<IAssociation12>;
type EntityArrayResponseType = HttpResponse<IAssociation12[]>;

@Injectable({ providedIn: 'root' })
export class Association12Service {
  public resourceUrl = SERVER_API_URL + 'api/association-12-s';

  constructor(protected http: HttpClient) {}

  create(association12: IAssociation12): Observable<EntityResponseType> {
    return this.http.post<IAssociation12>(this.resourceUrl, association12, { observe: 'response' });
  }

  update(association12: IAssociation12): Observable<EntityResponseType> {
    return this.http.put<IAssociation12>(this.resourceUrl, association12, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAssociation12>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAssociation12[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
