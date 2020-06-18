import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { FormulationentityDetailComponent } from 'app/entities/formulationentity/formulationentity-detail.component';
import { Formulationentity } from 'app/shared/model/formulationentity.model';

describe('Component Tests', () => {
  describe('Formulationentity Management Detail Component', () => {
    let comp: FormulationentityDetailComponent;
    let fixture: ComponentFixture<FormulationentityDetailComponent>;
    const route = ({ data: of({ formulationentity: new Formulationentity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [FormulationentityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormulationentityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormulationentityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formulationentity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formulationentity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
