import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { CourseComponent } from './course.component';

import { CourseRoute } from './course.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([CourseRoute])],
  declarations: [CourseComponent],
})
export class CourseModule {}