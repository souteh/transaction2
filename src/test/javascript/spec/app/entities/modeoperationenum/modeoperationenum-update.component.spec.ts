import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { ModeoperationenumUpdateComponent } from 'app/entities/modeoperationenum/modeoperationenum-update.component';
import { ModeoperationenumService } from 'app/entities/modeoperationenum/modeoperationenum.service';
import { Modeoperationenum } from 'app/shared/model/modeoperationenum.model';

describe('Component Tests', () => {
  describe('Modeoperationenum Management Update Component', () => {
    let comp: ModeoperationenumUpdateComponent;
    let fixture: ComponentFixture<ModeoperationenumUpdateComponent>;
    let service: ModeoperationenumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [ModeoperationenumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModeoperationenumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeoperationenumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeoperationenumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Modeoperationenum(123);
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
        const entity = new Modeoperationenum();
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
