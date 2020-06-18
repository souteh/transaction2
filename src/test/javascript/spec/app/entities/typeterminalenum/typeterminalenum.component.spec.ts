import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Transaction2TestModule } from '../../../test.module';
import { TypeterminalenumComponent } from 'app/entities/typeterminalenum/typeterminalenum.component';
import { TypeterminalenumService } from 'app/entities/typeterminalenum/typeterminalenum.service';
import { Typeterminalenum } from 'app/shared/model/typeterminalenum.model';

describe('Component Tests', () => {
  describe('Typeterminalenum Management Component', () => {
    let comp: TypeterminalenumComponent;
    let fixture: ComponentFixture<TypeterminalenumComponent>;
    let service: TypeterminalenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeterminalenumComponent],
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
        .overrideTemplate(TypeterminalenumComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeterminalenumComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeterminalenumService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typeterminalenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeterminalenums && comp.typeterminalenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typeterminalenum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeterminalenums && comp.typeterminalenums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
