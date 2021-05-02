import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';

import { DepartmentComponent } from './department.component';
import { DepartmentDetailComponent } from './department-detail.component';
import { AccountDepartmentComponent } from './account-department.component';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({ providedIn: 'root' })
export class CourseResolve implements Resolve<String> {
  resolve(route: ActivatedRouteSnapshot): Observable<String> {
    return route.params['uuid'];
  }
}
export const departmentRoute: Routes = [
  {
    path: '',
    component: DepartmentComponent,
    data: {
      pageTitle: 'Department manager',
    },
  },
  {
    path: ':uuid',
    component: DepartmentDetailComponent,
    resolve: {
      courseUUID: CourseResolve,
    },
    data: {
      pageTitle: 'Course detail',
    },
  },
  {
    path: 'manager/:uuid',
    component: AccountDepartmentComponent,
    resolve: {
      departmentUUID: CourseResolve,
    },
    data: {
      pageTitle: 'Department account manager',
    },
  },
];
