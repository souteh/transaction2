import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormulationentity } from 'app/shared/model/formulationentity.model';

type EntityResponseType = HttpResponse<IFormulationentity>;
type EntityArrayResponseType = HttpResponse<IFormulationentity[]>;

@Injectable({ providedIn: 'root' })
export class FormulationentityService {
  public resourceUrl = SERVER_API_URL + 'api/formulationentities';

  constructor(protected http: HttpClient) {}

  create(formulationentity: IFormulationentity): Observable<EntityResponseType> {
    return this.http.post<IFormulationentity>(this.resourceUrl, formulationentity, { observe: 'response' });
  }

  update(formulationentity: IFormulationentity): Observable<EntityResponseType> {
    return this.http.put<IFormulationentity>(this.resourceUrl, formulationentity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormulationentity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormulationentity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
