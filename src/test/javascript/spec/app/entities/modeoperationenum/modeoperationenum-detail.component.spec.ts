import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { ModeoperationenumDetailComponent } from 'app/entities/modeoperationenum/modeoperationenum-detail.component';
import { Modeoperationenum } from 'app/shared/model/modeoperationenum.model';

describe('Component Tests', () => {
  describe('Modeoperationenum Management Detail Component', () => {
    let comp: ModeoperationenumDetailComponent;
    let fixture: ComponentFixture<ModeoperationenumDetailComponent>;
    const route = ({ data: of({ modeoperationenum: new Modeoperationenum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [ModeoperationenumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModeoperationenumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModeoperationenumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modeoperationenum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modeoperationenum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
