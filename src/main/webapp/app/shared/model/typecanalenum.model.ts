export interface ITypecanalenum {
  id?: number;
  terminal?: string;
  online?: string;
}

export class Typecanalenum implements ITypecanalenum {
  constructor(public id?: number, public terminal?: string, public online?: string) {}
}
