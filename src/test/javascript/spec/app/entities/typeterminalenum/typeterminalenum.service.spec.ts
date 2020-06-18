import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypeterminalenumService } from 'app/entities/typeterminalenum/typeterminalenum.service';
import { ITypeterminalenum, Typeterminalenum } from 'app/shared/model/typeterminalenum.model';

describe('Service Tests', () => {
  describe('Typeterminalenum Service', () => {
    let injector: TestBed;
    let service: TypeterminalenumService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypeterminalenum;
    let expectedResult: ITypeterminalenum | ITypeterminalenum[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypeterminalenumService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Typeterminalenum(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Typeterminalenum', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Typeterminalenum()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Typeterminalenum', () => {
        const returnedFromService = Object.assign(
          {
            t2020: 'BBBBBB',
            t2030: 'BBBBBB',
            b2062: 'BBBBBB',
            pda: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Typeterminalenum', () => {
        const returnedFromService = Object.assign(
          {
            t2020: 'BBBBBB',
            t2030: 'BBBBBB',
            b2062: 'BBBBBB',
            pda: 'BBBBBB',
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

      it('should delete a Typeterminalenum', () => {
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
