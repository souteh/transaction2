export interface IClientglentity {
  id?: number;
  nom?: string;
  prenom?: string;
  cin?: string;
  telephone?: string;
  commentaire?: string;
}

export class Clientglentity implements IClientglentity {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public cin?: string,
    public telephone?: string,
    public commentaire?: string
  ) {}
}
