import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';

import { CourseAndDepartmentComponent } from './course-and-department.component';

import { CourseAndDepartmentDetailComponent } from './detail.component';

import{AccountClassComponent} from './account-class.component'

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<String> {
  resolve(route: ActivatedRouteSnapshot): Observable<String> {
    return route.params['uuid'];
  }
}
@Injectable({ providedIn: 'root' })
export class DetailResolve2 implements Resolve<String> {
  resolve(route: ActivatedRouteSnapshot): Observable<String> {
    return route.params['uuid'];
  }
}
export const CourseAndDepartmentRoute: Routes = [
  {
    path: '',
    component: CourseAndDepartmentComponent,
    data: {
      pageTitle: 'Course and department manager',
    },
  },
  {
    path: ':uuid',
    component: CourseAndDepartmentDetailComponent,
    resolve: {
      courseAndDepartmentUUID: DetailResolve,
    },
    data: {
      pageTitle: 'Course and department manager',
    },
  },
  {
    path: 'class/:uuid',
    component: AccountClassComponent,
    resolve: {
      classUUID: DetailResolve2,
    },
    data: {
      pageTitle: 'Course and department manager',
    },
  },

]
