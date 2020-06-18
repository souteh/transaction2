import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { Association12DetailComponent } from 'app/entities/association-12/association-12-detail.component';
import { Association12 } from 'app/shared/model/association-12.model';

describe('Component Tests', () => {
  describe('Association12 Management Detail Component', () => {
    let comp: Association12DetailComponent;
    let fixture: ComponentFixture<Association12DetailComponent>;
    const route = ({ data: of({ association12: new Association12(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [Association12DetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Association12DetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Association12DetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load association12 on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.association12).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
