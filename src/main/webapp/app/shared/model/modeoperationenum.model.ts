export interface IModeoperationenum {
  id?: number;
  automatique?: string;
  manuel?: string;
}

export class Modeoperationenum implements IModeoperationenum {
  constructor(public id?: number, public automatique?: string, public manuel?: string) {}
}
