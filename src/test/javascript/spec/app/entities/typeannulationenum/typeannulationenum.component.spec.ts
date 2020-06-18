import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Transaction2TestModule } from '../../../test.module';
import { TypeannulationenumComponent } from 'app/entities/typeannulationenum/typeannulationenum.component';
import { TypeannulationenumService } from 'app/entities/typeannulationenum/typeannulationenum.service';
import { Typeannulationenum } from 'app/shared/model/typeannulationenum.model';

describe('Component Tests', () => {
  describe('Typeannulationenum Management Component', () => {
    let comp: TypeannulationenumComponent;
    let fixture: ComponentFixture<TypeannulationenumComponent>;
    let service: TypeannulationenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeannulationenumComponent],
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
        .overrideTemplate(TypeannulationenumComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeannulationenumComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeannulationenumService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typeannulationenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeannulationenums && comp.typeannulationenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typeannulationenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeannulationenums && comp.typeannulationenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
