export interface ICatonlinecanalenum {
  id?: number;
  pel?: string;
  ptel?: string;
}

export class Catonlinecanalenum implements ICatonlinecanalenum {
  constructor(public id?: number, public pel?: string, public ptel?: string) {}
}
