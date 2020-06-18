export interface IPdventity {
  id?: number;
  idpdv?: number;
  referencepdv?: string;
}

export class Pdventity implements IPdventity {
  constructor(public id?: number, public idpdv?: number, public referencepdv?: string) {}
}
