import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FormulationentityService } from 'app/entities/formulationentity/formulationentity.service';
import { IFormulationentity, Formulationentity } from 'app/shared/model/formulationentity.model';

describe('Service Tests', () => {
  describe('Formulationentity Service', () => {
    let injector: TestBed;
    let service: FormulationentityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFormulationentity;
    let expectedResult: IFormulationentity | IFormulationentity[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FormulationentityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Formulationentity(0, 0, 0, 'AAAAAAA', false, 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Formulationentity', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Formulationentity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Formulationentity', () => {
        const returnedFromService = Object.assign(
          {
            identifiantconc: 1,
            idformulation: 1,
            codeproduit: 'BBBBBB',
            formcomplete: true,
            designation: 'BBBBBB',
            misecomb: 'BBBBBB',
            misetotale: 1,
            numform: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Formulationentity', () => {
        const returnedFromService = Object.assign(
          {
            identifiantconc: 1,
            idformulation: 1,
            codeproduit: 'BBBBBB',
            formcomplete: true,
            designation: 'BBBBBB',
            misecomb: 'BBBBBB',
            misetotale: 1,
            numform: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Formulationentity', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
