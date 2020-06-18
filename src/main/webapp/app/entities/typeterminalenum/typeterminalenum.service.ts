import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeterminalenum } from 'app/shared/model/typeterminalenum.model';

type EntityResponseType = HttpResponse<ITypeterminalenum>;
type EntityArrayResponseType = HttpResponse<ITypeterminalenum[]>;

@Injectable({ providedIn: 'root' })
export class TypeterminalenumService {
  public resourceUrl = SERVER_API_URL + 'api/typeterminalenums';

  constructor(protected http: HttpClient) {}

  create(typeterminalenum: ITypeterminalenum): Observable<EntityResponseType> {
    return this.http.post<ITypeterminalenum>(this.resourceUrl, typeterminalenum, { observe: 'response' });
  }

  update(typeterminalenum: ITypeterminalenum): Observable<EntityResponseType> {
    return this.http.put<ITypeterminalenum>(this.resourceUrl, typeterminalenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeterminalenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeterminalenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
