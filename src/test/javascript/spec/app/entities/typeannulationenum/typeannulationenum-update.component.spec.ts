import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeannulationenumUpdateComponent } from 'app/entities/typeannulationenum/typeannulationenum-update.component';
import { TypeannulationenumService } from 'app/entities/typeannulationenum/typeannulationenum.service';
import { Typeannulationenum } from 'app/shared/model/typeannulationenum.model';

describe('Component Tests', () => {
  describe('Typeannulationenum Management Update Component', () => {
    let comp: TypeannulationenumUpdateComponent;
    let fixture: ComponentFixture<TypeannulationenumUpdateComponent>;
    let service: TypeannulationenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeannulationenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeannulationenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeannulationenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeannulationenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Typeannulationenum(123);
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
        const entity = new Typeannulationenum();
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
