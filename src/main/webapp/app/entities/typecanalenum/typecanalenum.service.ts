import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypecanalenum } from 'app/shared/model/typecanalenum.model';

type EntityResponseType = HttpResponse<ITypecanalenum>;
type EntityArrayResponseType = HttpResponse<ITypecanalenum[]>;

@Injectable({ providedIn: 'root' })
export class TypecanalenumService {
  public resourceUrl = SERVER_API_URL + 'api/typecanalenums';

  constructor(protected http: HttpClient) {}

  create(typecanalenum: ITypecanalenum): Observable<EntityResponseType> {
    return this.http.post<ITypecanalenum>(this.resourceUrl, typecanalenum, { observe: 'response' });
  }

  update(typecanalenum: ITypecanalenum): Observable<EntityResponseType> {
    return this.http.put<ITypecanalenum>(this.resourceUrl, typecanalenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypecanalenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypecanalenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
