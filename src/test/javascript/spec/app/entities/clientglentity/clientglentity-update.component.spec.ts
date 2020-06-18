import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { ClientglentityUpdateComponent } from 'app/entities/clientglentity/clientglentity-update.component';
import { ClientglentityService } from 'app/entities/clientglentity/clientglentity.service';
import { Clientglentity } from 'app/shared/model/clientglentity.model';

describe('Component Tests', () => {
  describe('Clientglentity Management Update Component', () => {
    let comp: ClientglentityUpdateComponent;
    let fixture: ComponentFixture<ClientglentityUpdateComponent>;
    let service: ClientglentityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [ClientglentityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ClientglentityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClientglentityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClientglentityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Clientglentity(123);
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
        const entity = new Clientglentity();
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
