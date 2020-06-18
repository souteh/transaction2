import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeterminalenumDetailComponent } from 'app/entities/typeterminalenum/typeterminalenum-detail.component';
import { Typeterminalenum } from 'app/shared/model/typeterminalenum.model';

describe('Component Tests', () => {
  describe('Typeterminalenum Management Detail Component', () => {
    let comp: TypeterminalenumDetailComponent;
    let fixture: ComponentFixture<TypeterminalenumDetailComponent>;
    const route = ({ data: of({ typeterminalenum: new Typeterminalenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeterminalenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeterminalenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeterminalenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeterminalenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeterminalenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
