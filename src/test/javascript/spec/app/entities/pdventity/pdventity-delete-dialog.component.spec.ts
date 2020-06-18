import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Transaction2TestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PdventityDeleteDialogComponent } from 'app/entities/pdventity/pdventity-delete-dialog.component';
import { PdventityService } from 'app/entities/pdventity/pdventity.service';

describe('Component Tests', () => {
  describe('Pdventity Management Delete Component', () => {
    let comp: PdventityDeleteDialogComponent;
    let fixture: ComponentFixture<PdventityDeleteDialogComponent>;
    let service: PdventityService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [PdventityDeleteDialogComponent],
      })
        .overrideTemplate(PdventityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdventityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdventityService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
