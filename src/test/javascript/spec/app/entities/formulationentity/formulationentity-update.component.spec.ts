import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { FormulationentityUpdateComponent } from 'app/entities/formulationentity/formulationentity-update.component';
import { FormulationentityService } from 'app/entities/formulationentity/formulationentity.service';
import { Formulationentity } from 'app/shared/model/formulationentity.model';

describe('Component Tests', () => {
  describe('Formulationentity Management Update Component', () => {
    let comp: FormulationentityUpdateComponent;
    let fixture: ComponentFixture<FormulationentityUpdateComponent>;
    let service: FormulationentityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [FormulationentityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormulationentityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormulationentityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormulationentityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Formulationentity(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Formulationentity();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
