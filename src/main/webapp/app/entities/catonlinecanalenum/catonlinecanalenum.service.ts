import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICatonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';

type EntityResponseType = HttpResponse<ICatonlinecanalenum>;
type EntityArrayResponseType = HttpResponse<ICatonlinecanalenum[]>;

@Injectable({ providedIn: 'root' })
export class CatonlinecanalenumService {
  public resourceUrl = SERVER_API_URL + 'api/catonlinecanalenums';

  constructor(protected http: HttpClient) {}

  create(catonlinecanalenum: ICatonlinecanalenum): Observable<EntityResponseType> {
    return this.http.post<ICatonlinecanalenum>(this.resourceUrl, catonlinecanalenum, { observe: 'response' });
  }

  update(catonlinecanalenum: ICatonlinecanalenum): Observable<EntityResponseType> {
    return this.http.put<ICatonlinecanalenum>(this.resourceUrl, catonlinecanalenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICatonlinecanalenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICatonlinecanalenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
