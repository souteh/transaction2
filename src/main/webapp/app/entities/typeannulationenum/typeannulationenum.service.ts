import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeannulationenum } from 'app/shared/model/typeannulationenum.model';

type EntityResponseType = HttpResponse<ITypeannulationenum>;
type EntityArrayResponseType = HttpResponse<ITypeannulationenum[]>;

@Injectable({ providedIn: 'root' })
export class TypeannulationenumService {
  public resourceUrl = SERVER_API_URL + 'api/typeannulationenums';

  constructor(protected http: HttpClient) {}

  create(typeannulationenum: ITypeannulationenum): Observable<EntityResponseType> {
    return this.http.post<ITypeannulationenum>(this.resourceUrl, typeannulationenum, { observe: 'response' });
  }

  update(typeannulationenum: ITypeannulationenum): Observable<EntityResponseType> {
    return this.http.put<ITypeannulationenum>(this.resourceUrl, typeannulationenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeannulationenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeannulationenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
