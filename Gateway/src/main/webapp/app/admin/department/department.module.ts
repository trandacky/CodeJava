import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { DepartmentComponent } from './department.component';

import {  departmentRoute} from './department.route';
import { DepartmentDetailComponent} from './department-detail.component';
import { AccountDepartmentComponent} from './account-department.component';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild(departmentRoute)],
  declarations: [DepartmentComponent,DepartmentDetailComponent,AccountDepartmentComponent],
})
export class DepartmentModule {}