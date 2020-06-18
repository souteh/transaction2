import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { CatonlinecanalenumDetailComponent } from 'app/entities/catonlinecanalenum/catonlinecanalenum-detail.component';
import { Catonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';

describe('Component Tests', () => {
  describe('Catonlinecanalenum Management Detail Component', () => {
    let comp: CatonlinecanalenumDetailComponent;
    let fixture: ComponentFixture<CatonlinecanalenumDetailComponent>;
    const route = ({ data: of({ catonlinecanalenum: new Catonlinecanalenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [CatonlinecanalenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CatonlinecanalenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CatonlinecanalenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load catonlinecanalenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.catonlinecanalenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
