export interface IStatutticketenum {
  id?: number;
  valide?: string;
  annule?: string;
  paye?: string;
  rembourse?: string;
}

export class Statutticketenum implements IStatutticketenum {
  constructor(public id?: number, public valide?: string, public annule?: string, public paye?: string, public rembourse?: string) {}
}
