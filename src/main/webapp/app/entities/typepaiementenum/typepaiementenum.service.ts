import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypepaiementenum } from 'app/shared/model/typepaiementenum.model';

type EntityResponseType = HttpResponse<ITypepaiementenum>;
type EntityArrayResponseType = HttpResponse<ITypepaiementenum[]>;

@Injectable({ providedIn: 'root' })
export class TypepaiementenumService {
  public resourceUrl = SERVER_API_URL + 'api/typepaiementenums';

  constructor(protected http: HttpClient) {}

  create(typepaiementenum: ITypepaiementenum): Observable<EntityResponseType> {
    return this.http.post<ITypepaiementenum>(this.resourceUrl, typepaiementenum, { observe: 'response' });
  }

  update(typepaiementenum: ITypepaiementenum): Observable<EntityResponseType> {
    return this.http.put<ITypepaiementenum>(this.resourceUrl, typepaiementenum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypepaiementenum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypepaiementenum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
