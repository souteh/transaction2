import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypecanalenumDetailComponent } from 'app/entities/typecanalenum/typecanalenum-detail.component';
import { Typecanalenum } from 'app/shared/model/typecanalenum.model';

describe('Component Tests', () => {
  describe('Typecanalenum Management Detail Component', () => {
    let comp: TypecanalenumDetailComponent;
    let fixture: ComponentFixture<TypecanalenumDetailComponent>;
    const route = ({ data: of({ typecanalenum: new Typecanalenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypecanalenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypecanalenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypecanalenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typecanalenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typecanalenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
