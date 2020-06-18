export interface IFormulationentity {
  id?: number;
  identifiantconc?: number;
  idformulation?: number;
  codeproduit?: string;
  formcomplete?: boolean;
  designation?: string;
  misecomb?: string;
  misetotale?: number;
  numform?: number;
}

export class Formulationentity implements IFormulationentity {
  constructor(
    public id?: number,
    public identifiantconc?: number,
    public idformulation?: number,
    public codeproduit?: string,
    public formcomplete?: boolean,
    public designation?: string,
    public misecomb?: string,
    public misetotale?: number,
    public numform?: number
  ) {
    this.formcomplete = this.formcomplete || false;
  }
}
