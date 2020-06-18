import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStatutticketenum } from 'app/shared/model/statutticketenum.model';

type EntityResponseType = HttpResponse<IStatutticketenum>;
type EntityArrayResponseType = HttpResponse<IStatutticketenum[]>;

@Injectable({ providedIn: 'root' })
export class StatutticketenumService {
  public resourceUrl = SERVER_API_URL + 'api/statutticketenums';

  constructor(protected http: HttpClient) {}

  create(statutticketenum: IStatutticketenum): Observable<EntityResponseType> {
    return this.http.post<IStatutticketenum>(this.resourceUrl, statutticketenum, { observe: 'response' });
  }

  update(statutticketenum: IStatutticketenum): Observable<EntityResponseType> {
    return this.http.put<IStatutticketenum>(this.resourceUrl, statutticketenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatutticketenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatutticketenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
