import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeoperationenum } from 'app/shared/model/typeoperationenum.model';

type EntityResponseType = HttpResponse<ITypeoperationenum>;
type EntityArrayResponseType = HttpResponse<ITypeoperationenum[]>;

@Injectable({ providedIn: 'root' })
export class TypeoperationenumService {
  public resourceUrl = SERVER_API_URL + 'api/typeoperationenums';

  constructor(protected http: HttpClient) {}

  create(typeoperationenum: ITypeoperationenum): Observable<EntityResponseType> {
    return this.http.post<ITypeoperationenum>(this.resourceUrl, typeoperationenum, { observe: 'response' });
  }

  update(typeoperationenum: ITypeoperationenum): Observable<EntityResponseType> {
    return this.http.put<ITypeoperationenum>(this.resourceUrl, typeoperationenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeoperationenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeoperationenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
