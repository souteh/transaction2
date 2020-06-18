export interface ITypeterminalenum {
  id?: number;
  t2020?: string;
  t2030?: string;
  b2062?: string;
  pda?: string;
}

export class Typeterminalenum implements ITypeterminalenum {
  constructor(public id?: number, public t2020?: string, public t2030?: string, public b2062?: string, public pda?: string) {}
}
