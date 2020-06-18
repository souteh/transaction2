import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Transaction2TestModule } from '../../../test.module';
import { TypepaiementenumComponent } from 'app/entities/typepaiementenum/typepaiementenum.component';
import { TypepaiementenumService } from 'app/entities/typepaiementenum/typepaiementenum.service';
import { Typepaiementenum } from 'app/shared/model/typepaiementenum.model';

describe('Component Tests', () => {
  describe('Typepaiementenum Management Component', () => {
    let comp: TypepaiementenumComponent;
    let fixture: ComponentFixture<TypepaiementenumComponent>;
    let service: TypepaiementenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypepaiementenumComponent],
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
        .overrideTemplate(TypepaiementenumComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypepaiementenumComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypepaiementenumService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typepaiementenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typepaiementenums && comp.typepaiementenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typepaiementenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typepaiementenums && comp.typepaiementenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
