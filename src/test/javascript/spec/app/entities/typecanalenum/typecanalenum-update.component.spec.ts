import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypecanalenumUpdateComponent } from 'app/entities/typecanalenum/typecanalenum-update.component';
import { TypecanalenumService } from 'app/entities/typecanalenum/typecanalenum.service';
import { Typecanalenum } from 'app/shared/model/typecanalenum.model';

describe('Component Tests', () => {
  describe('Typecanalenum Management Update Component', () => {
    let comp: TypecanalenumUpdateComponent;
    let fixture: ComponentFixture<TypecanalenumUpdateComponent>;
    let service: TypecanalenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypecanalenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypecanalenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypecanalenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypecanalenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Typecanalenum(123);
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
        const entity = new Typecanalenum();
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
