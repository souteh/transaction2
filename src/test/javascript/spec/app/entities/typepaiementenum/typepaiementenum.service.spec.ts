import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypepaiementenumService } from 'app/entities/typepaiementenum/typepaiementenum.service';
import { ITypepaiementenum, Typepaiementenum } from 'app/shared/model/typepaiementenum.model';

describe('Service Tests', () => {
  describe('Typepaiementenum Service', () => {
    let injector: TestBed;
    let service: TypepaiementenumService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypepaiementenum;
    let expectedResult: ITypepaiementenum | ITypepaiementenum[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypepaiementenumService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Typepaiementenum(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Typepaiementenum', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Typepaiementenum()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Typepaiementenum', () => {
        const returnedFromService = Object.assign(
          {
            total: 'BBBBBB',
            avance: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Typepaiementenum', () => {
        const returnedFromService = Object.assign(
          {
            total: 'BBBBBB',
            avance: 'BBBBBB',
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

      it('should delete a Typepaiementenum', () => {
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
