import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';
import { CourseAndDepartmentRoute } from './course-and-department.route';
import{CourseAndDepartmentComponent} from './course-and-department.component'
import{CourseAndDepartmentDetailComponent} from './detail.component'
@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild(CourseAndDepartmentRoute)],
  declarations: [CourseAndDepartmentComponent,CourseAndDepartmentDetailComponent],
})
export class CourseAndDepartmentModule {}