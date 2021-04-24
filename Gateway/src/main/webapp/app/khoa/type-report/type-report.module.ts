import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { TypeReportComponent } from './type-report.component';

import { TypeReportRoute } from './type-report.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([TypeReportRoute])],
  declarations: [TypeReportComponent],
})
export class TypeReportModule {} 