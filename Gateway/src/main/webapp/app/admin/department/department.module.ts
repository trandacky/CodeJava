import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { DepartmentComponent } from './department.component';

import {  departmentRoute} from './department.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([departmentRoute])],
  declarations: [DepartmentComponent],
})
export class DepartmentModule {}