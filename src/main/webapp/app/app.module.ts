import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Transaction2SharedModule } from 'app/shared/shared.module';
import { Transaction2CoreModule } from 'app/core/core.module';
import { Transaction2AppRoutingModule } from './app-routing.module';
import { Transaction2HomeModule } from './home/home.module';
import { Transaction2EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Transaction2SharedModule,
    Transaction2CoreModule,
    Transaction2HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Transaction2EntityModule,
    Transaction2AppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Transaction2AppModule {}
