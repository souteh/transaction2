import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClientglentity } from 'app/shared/model/clientglentity.model';

type EntityResponseType = HttpResponse<IClientglentity>;
type EntityArrayResponseType = HttpResponse<IClientglentity[]>;

@Injectable({ providedIn: 'root' })
export class ClientglentityService {
  public resourceUrl = SERVER_API_URL + 'api/clientglentities';

  constructor(protected http: HttpClient) {}

  create(clientglentity: IClientglentity): Observable<EntityResponseType> {
    return this.http.post<IClientglentity>(this.resourceUrl, clientglentity, { observe: 'response' });
  }

  update(clientglentity: IClientglentity): Observable<EntityResponseType> {
    return this.http.put<IClientglentity>(this.resourceUrl, clientglentity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClientglentity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClientglentity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
