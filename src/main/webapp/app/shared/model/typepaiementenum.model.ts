export interface ITypepaiementenum {
  id?: number;
  total?: string;
  avance?: string;
}

export class Typepaiementenum implements ITypepaiementenum {
  constructor(public id?: number, public total?: string, public avance?: string) {}
}
