import { IClientglentity } from 'app/shared/model/clientglentity.model';

export interface IAssociation12 {
  id?: number;
  identifiantconc?: number;
  id?: IClientglentity;
}

export class Association12 implements IAssociation12 {
  constructor(public id?: number, public identifiantconc?: number, public id?: IClientglentity) {}
}
