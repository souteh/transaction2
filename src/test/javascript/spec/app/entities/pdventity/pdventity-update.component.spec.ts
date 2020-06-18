import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { PdventityUpdateComponent } from 'app/entities/pdventity/pdventity-update.component';
import { PdventityService } from 'app/entities/pdventity/pdventity.service';
import { Pdventity } from 'app/shared/model/pdventity.model';

describe('Component Tests', () => {
  describe('Pdventity Management Update Component', () => {
    let comp: PdventityUpdateComponent;
    let fixture: ComponentFixture<PdventityUpdateComponent>;
    let service: PdventityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [PdventityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PdventityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PdventityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdventityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Pdventity(123);
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
        const entity = new Pdventity();
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
