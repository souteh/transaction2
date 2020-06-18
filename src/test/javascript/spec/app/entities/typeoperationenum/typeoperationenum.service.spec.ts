import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypeoperationenumService } from 'app/entities/typeoperationenum/typeoperationenum.service';
import { ITypeoperationenum, Typeoperationenum } from 'app/shared/model/typeoperationenum.model';

describe('Service Tests', () => {
  describe('Typeoperationenum Service', () => {
    let injector: TestBed;
    let service: TypeoperationenumService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypeoperationenum;
    let expectedResult: ITypeoperationenum | ITypeoperationenum[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypeoperationenumService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Typeoperationenum(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Typeoperationenum', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Typeoperationenum()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Typeoperationenum', () => {
        const returnedFromService = Object.assign(
          {
            pari: 'BBBBBB',
            annulation: 'BBBBBB',
            paiement: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Typeoperationenum', () => {
        const returnedFromService = Object.assign(
          {
            pari: 'BBBBBB',
            annulation: 'BBBBBB',
            paiement: 'BBBBBB',
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

      it('should delete a Typeoperationenum', () => {
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
