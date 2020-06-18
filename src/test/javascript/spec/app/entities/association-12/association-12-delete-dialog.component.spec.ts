import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Transaction2TestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { Association12DeleteDialogComponent } from 'app/entities/association-12/association-12-delete-dialog.component';
import { Association12Service } from 'app/entities/association-12/association-12.service';

describe('Component Tests', () => {
  describe('Association12 Management Delete Component', () => {
    let comp: Association12DeleteDialogComponent;
    let fixture: ComponentFixture<Association12DeleteDialogComponent>;
    let service: Association12Service;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [Association12DeleteDialogComponent],
      })
        .overrideTemplate(Association12DeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Association12DeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Association12Service);
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
