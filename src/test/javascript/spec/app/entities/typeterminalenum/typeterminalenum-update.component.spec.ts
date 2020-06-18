import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { TypeterminalenumUpdateComponent } from 'app/entities/typeterminalenum/typeterminalenum-update.component';
import { TypeterminalenumService } from 'app/entities/typeterminalenum/typeterminalenum.service';
import { Typeterminalenum } from 'app/shared/model/typeterminalenum.model';

describe('Component Tests', () => {
  describe('Typeterminalenum Management Update Component', () => {
    let comp: TypeterminalenumUpdateComponent;
    let fixture: ComponentFixture<TypeterminalenumUpdateComponent>;
    let service: TypeterminalenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [TypeterminalenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeterminalenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeterminalenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeterminalenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Typeterminalenum(123);
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
        const entity = new Typeterminalenum();
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
