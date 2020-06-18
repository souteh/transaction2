import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { Association12UpdateComponent } from 'app/entities/association-12/association-12-update.component';
import { Association12Service } from 'app/entities/association-12/association-12.service';
import { Association12 } from 'app/shared/model/association-12.model';

describe('Component Tests', () => {
  describe('Association12 Management Update Component', () => {
    let comp: Association12UpdateComponent;
    let fixture: ComponentFixture<Association12UpdateComponent>;
    let service: Association12Service;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [Association12UpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Association12UpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Association12UpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Association12Service);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Association12(123);
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
        const entity = new Association12();
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
