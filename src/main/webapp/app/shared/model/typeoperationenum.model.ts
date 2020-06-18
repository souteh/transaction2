export interface ITypeoperationenum {
  id?: number;
  pari?: string;
  annulation?: string;
  paiement?: string;
}

export class Typeoperationenum implements ITypeoperationenum {
  constructor(public id?: number, public pari?: string, public annulation?: string, public paiement?: string) {}
}
