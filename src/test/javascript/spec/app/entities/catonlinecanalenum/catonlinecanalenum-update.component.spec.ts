import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { CatonlinecanalenumUpdateComponent } from 'app/entities/catonlinecanalenum/catonlinecanalenum-update.component';
import { CatonlinecanalenumService } from 'app/entities/catonlinecanalenum/catonlinecanalenum.service';
import { Catonlinecanalenum } from 'app/shared/model/catonlinecanalenum.model';

describe('Component Tests', () => {
  describe('Catonlinecanalenum Management Update Component', () => {
    let comp: CatonlinecanalenumUpdateComponent;
    let fixture: ComponentFixture<CatonlinecanalenumUpdateComponent>;
    let service: CatonlinecanalenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [CatonlinecanalenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CatonlinecanalenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CatonlinecanalenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CatonlinecanalenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Catonlinecanalenum(123);
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
        const entity = new Catonlinecanalenum();
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
