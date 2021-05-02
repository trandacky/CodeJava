import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { Observable } from 'rxjs';

import { CadComponent } from './cad.component';
import { ClassComponent } from './class.component';
import { AccountClassComponent } from './account-class.component';
@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<String> {
  resolve(route: ActivatedRouteSnapshot): Observable<String> {
    return route.params['uuid'];
  }
}
export const ClassRoute: Routes = [
  {
    path: '',
    component: CadComponent,
    data: {
      pageTitle: 'Class manager',
    },
  },
  {
    path: ':uuid',
    component: ClassComponent,
    resolve: {
      courseAndDepartmentUUID: DetailResolve,
    },
    data: {
      pageTitle: 'Class manager',
    },
  },
  {
    path: 'class/:uuid',
    component: AccountClassComponent,
    resolve: {
      classUUID: DetailResolve,
    },
    data: {
      pageTitle: 'Class manager',
    },
  },
]