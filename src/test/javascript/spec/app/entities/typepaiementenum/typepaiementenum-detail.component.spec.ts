import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypepaiementenumDetailComponent } from 'app/entities/typepaiementenum/typepaiementenum-detail.component';
import { Typepaiementenum } from 'app/shared/model/typepaiementenum.model';

describe('Component Tests', () => {
  describe('Typepaiementenum Management Detail Component', () => {
    let comp: TypepaiementenumDetailComponent;
    let fixture: ComponentFixture<TypepaiementenumDetailComponent>;
    const route = ({ data: of({ typepaiementenum: new Typepaiementenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypepaiementenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypepaiementenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypepaiementenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typepaiementenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typepaiementenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
