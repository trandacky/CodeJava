import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { SVReportComponent } from './svreport.component';

import { SVReportRoute } from './svreport.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([SVReportRoute])],
  declarations: [SVReportComponent],
})
export class SVReportModule {} 