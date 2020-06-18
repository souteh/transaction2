import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModeoperationenum } from 'app/shared/model/modeoperationenum.model';

type EntityResponseType = HttpResponse<IModeoperationenum>;
type EntityArrayResponseType = HttpResponse<IModeoperationenum[]>;

@Injectable({ providedIn: 'root' })
export class ModeoperationenumService {
  public resourceUrl = SERVER_API_URL + 'api/modeoperationenums';

  constructor(protected http: HttpClient) {}

  create(modeoperationenum: IModeoperationenum): Observable<EntityResponseType> {
    return this.http.post<IModeoperationenum>(this.resourceUrl, modeoperationenum, { observe: 'response' });
  }

  update(modeoperationenum: IModeoperationenum): Observable<EntityResponseType> {
    return this.http.put<IModeoperationenum>(this.resourceUrl, modeoperationenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModeoperationenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModeoperationenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
