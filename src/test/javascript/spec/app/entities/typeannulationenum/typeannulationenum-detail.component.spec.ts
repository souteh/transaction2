import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeannulationenumDetailComponent } from 'app/entities/typeannulationenum/typeannulationenum-detail.component';
import { Typeannulationenum } from 'app/shared/model/typeannulationenum.model';

describe('Component Tests', () => {
  describe('Typeannulationenum Management Detail Component', () => {
    let comp: TypeannulationenumDetailComponent;
    let fixture: ComponentFixture<TypeannulationenumDetailComponent>;
    const route = ({ data: of({ typeannulationenum: new Typeannulationenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeannulationenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeannulationenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeannulationenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeannulationenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeannulationenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
