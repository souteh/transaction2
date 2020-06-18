import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeoperationenumUpdateComponent } from 'app/entities/typeoperationenum/typeoperationenum-update.component';
import { TypeoperationenumService } from 'app/entities/typeoperationenum/typeoperationenum.service';
import { Typeoperationenum } from 'app/shared/model/typeoperationenum.model';

describe('Component Tests', () => {
  describe('Typeoperationenum Management Update Component', () => {
    let comp: TypeoperationenumUpdateComponent;
    let fixture: ComponentFixture<TypeoperationenumUpdateComponent>;
    let service: TypeoperationenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeoperationenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeoperationenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeoperationenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeoperationenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Typeoperationenum(123);
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
        const entity = new Typeoperationenum();
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
