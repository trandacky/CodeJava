import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { Observable } from 'rxjs';

import { ListReportComponent } from './list-report.component';
import { Report2Component } from './report2.component';

@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<Number> {
  resolve(route: ActivatedRouteSnapshot): Observable<Number> {
    return route.params['id'];
  }
}
export const Report2Route: Routes = [
  {
    path: '',
    component: ListReportComponent,
    data: {
      pageTitle: 'Class Report',
    },
  },
  {
    path: ':id',
    component: Report2Component,
    resolve: {
      reportId: DetailResolve,
    },
    data: {
      pageTitle: 'Class Report',
    },
  }
];