import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Transaction2TestModule } from '../../../test.module';
import { TypecanalenumComponent } from 'app/entities/typecanalenum/typecanalenum.component';
import { TypecanalenumService } from 'app/entities/typecanalenum/typecanalenum.service';
import { Typecanalenum } from 'app/shared/model/typecanalenum.model';

describe('Component Tests', () => {
  describe('Typecanalenum Management Component', () => {
    let comp: TypecanalenumComponent;
    let fixture: ComponentFixture<TypecanalenumComponent>;
    let service: TypecanalenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypecanalenumComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(TypecanalenumComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypecanalenumComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypecanalenumService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typecanalenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typecanalenums && comp.typecanalenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typecanalenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typecanalenums && comp.typecanalenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
