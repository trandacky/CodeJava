import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { ClassComponent } from './class.component';

import { ClassRoute } from './class.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([ClassRoute])],
  declarations: [ClassComponent],
})
export class ClassModule {} 