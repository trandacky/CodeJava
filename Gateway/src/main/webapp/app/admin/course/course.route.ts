import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';

import { CourseComponent } from './course.component';
import{CourseDetailComponent} from './course-detail.component'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({ providedIn: 'root' })
export class CourseResolve implements Resolve<String> {
  resolve(route: ActivatedRouteSnapshot): Observable<String> {
    return route.params['uuid'];
  }
}
export const CourseRoute: Routes = [
  {
    path: '',
    component: CourseComponent,
    data: {
      pageTitle: 'Course manager',
    },
  },
  {
    path: ':uuid',
    component: CourseDetailComponent,
    resolve: {
      departmentUUID: CourseResolve,
    },
    data: {
      pageTitle: 'Department detail',
    },
  },

]
