import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { StatutticketenumDetailComponent } from 'app/entities/statutticketenum/statutticketenum-detail.component';
import { Statutticketenum } from 'app/shared/model/statutticketenum.model';

describe('Component Tests', () => {
  describe('Statutticketenum Management Detail Component', () => {
    let comp: StatutticketenumDetailComponent;
    let fixture: ComponentFixture<StatutticketenumDetailComponent>;
    const route = ({ data: of({ statutticketenum: new Statutticketenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [StatutticketenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StatutticketenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatutticketenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load statutticketenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.statutticketenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
