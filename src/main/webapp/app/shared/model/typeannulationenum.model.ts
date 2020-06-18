export interface ITypeannulationenum {
  id?: number;
  client?: string;
  machine?: string;
  exceptionnelle?: string;
  systeme?: string;
}

export class Typeannulationenum implements ITypeannulationenum {
  constructor(
    public id?: number,
    public client?: string,
    public machine?: string,
    public exceptionnelle?: string,
    public systeme?: string
  ) {}
}
