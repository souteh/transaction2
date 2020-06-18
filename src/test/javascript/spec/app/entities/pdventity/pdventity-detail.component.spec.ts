import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { PdventityDetailComponent } from 'app/entities/pdventity/pdventity-detail.component';
import { Pdventity } from 'app/shared/model/pdventity.model';

describe('Component Tests', () => {
  describe('Pdventity Management Detail Component', () => {
    let comp: PdventityDetailComponent;
    let fixture: ComponentFixture<PdventityDetailComponent>;
    const route = ({ data: of({ pdventity: new Pdventity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [PdventityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PdventityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdventityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pdventity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pdventity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
