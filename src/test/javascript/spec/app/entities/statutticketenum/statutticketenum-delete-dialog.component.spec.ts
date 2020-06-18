import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Transaction2TestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { StatutticketenumDeleteDialogComponent } from 'app/entities/statutticketenum/statutticketenum-delete-dialog.component';
import { StatutticketenumService } from 'app/entities/statutticketenum/statutticketenum.service';

describe('Component Tests', () => {
  describe('Statutticketenum Management Delete Component', () => {
    let comp: StatutticketenumDeleteDialogComponent;
    let fixture: ComponentFixture<StatutticketenumDeleteDialogComponent>;
    let service: StatutticketenumService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [StatutticketenumDeleteDialogComponent],
      })
        .overrideTemplate(StatutticketenumDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatutticketenumDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatutticketenumService);
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
