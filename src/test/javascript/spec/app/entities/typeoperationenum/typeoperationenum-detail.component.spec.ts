import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeoperationenumDetailComponent } from 'app/entities/typeoperationenum/typeoperationenum-detail.component';
import { Typeoperationenum } from 'app/shared/model/typeoperationenum.model';

describe('Component Tests', () => {
  describe('Typeoperationenum Management Detail Component', () => {
    let comp: TypeoperationenumDetailComponent;
    let fixture: ComponentFixture<TypeoperationenumDetailComponent>;
    const route = ({ data: of({ typeoperationenum: new Typeoperationenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeoperationenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeoperationenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeoperationenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeoperationenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeoperationenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
