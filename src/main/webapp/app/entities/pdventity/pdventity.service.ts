import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPdventity } from 'app/shared/model/pdventity.model';

type EntityResponseType = HttpResponse<IPdventity>;
type EntityArrayResponseType = HttpResponse<IPdventity[]>;

@Injectable({ providedIn: 'root' })
export class PdventityService {
  public resourceUrl = SERVER_API_URL + 'api/pdventities';

  constructor(protected http: HttpClient) {}

  create(pdventity: IPdventity): Observable<EntityResponseType> {
    return this.http.post<IPdventity>(this.resourceUrl, pdventity, { observe: 'response' });
  }

  update(pdventity: IPdventity): Observable<EntityResponseType> {
    return this.http.put<IPdventity>(this.resourceUrl, pdventity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPdventity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPdventity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
