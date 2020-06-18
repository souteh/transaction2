import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypeannulationenumService } from 'app/entities/typeannulationenum/typeannulationenum.service';
import { ITypeannulationenum, Typeannulationenum } from 'app/shared/model/typeannulationenum.model';

describe('Service Tests', () => {
  describe('Typeannulationenum Service', () => {
    let injector: TestBed;
    let service: TypeannulationenumService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypeannulationenum;
    let expectedResult: ITypeannulationenum | ITypeannulationenum[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypeannulationenumService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Typeannulationenum(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Typeannulationenum', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Typeannulationenum()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Typeannulationenum', () => {
        const returnedFromService = Object.assign(
          {
            client: 'BBBBBB',
            machine: 'BBBBBB',
            exceptionnelle: 'BBBBBB',
            systeme: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Typeannulationenum', () => {
        const returnedFromService = Object.assign(
          {
            client: 'BBBBBB',
            machine: 'BBBBBB',
            exceptionnelle: 'BBBBBB',
            systeme: 'BBBBBB',
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

      it('should delete a Typeannulationenum', () => {
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
