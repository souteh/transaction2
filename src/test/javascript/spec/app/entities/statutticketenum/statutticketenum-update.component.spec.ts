import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { StatutticketenumUpdateComponent } from 'app/entities/statutticketenum/statutticketenum-update.component';
import { StatutticketenumService } from 'app/entities/statutticketenum/statutticketenum.service';
import { Statutticketenum } from 'app/shared/model/statutticketenum.model';

describe('Component Tests', () => {
  describe('Statutticketenum Management Update Component', () => {
    let comp: StatutticketenumUpdateComponent;
    let fixture: ComponentFixture<StatutticketenumUpdateComponent>;
    let service: StatutticketenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [StatutticketenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StatutticketenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatutticketenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatutticketenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Statutticketenum(123);
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
        const entity = new Statutticketenum();
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
