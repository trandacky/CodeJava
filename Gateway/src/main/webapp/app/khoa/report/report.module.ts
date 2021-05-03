import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { ReportRoute } from './report.route';

import { ReportComponent } from './report.component';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([ReportRoute])],
  declarations: [ReportComponent],
})
export class ReportModule {} 