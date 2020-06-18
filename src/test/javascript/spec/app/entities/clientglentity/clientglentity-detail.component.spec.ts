import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Transaction2TestModule } from '../../../test.module';
import { ClientglentityDetailComponent } from 'app/entities/clientglentity/clientglentity-detail.component';
import { Clientglentity } from 'app/shared/model/clientglentity.model';

describe('Component Tests', () => {
  describe('Clientglentity Management Detail Component', () => {
    let comp: ClientglentityDetailComponent;
    let fixture: ComponentFixture<ClientglentityDetailComponent>;
    const route = ({ data: of({ clientglentity: new Clientglentity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Transaction2TestModule],
        declarations: [ClientglentityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ClientglentityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClientglentityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load clientglentity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.clientglentity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
