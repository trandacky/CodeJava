import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { Observable } from 'rxjs';

import { SVReportComponent } from './svreport.component';
import { SVDoReportComponent } from './sv-do-report.component';

@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<Number> {
  resolve(route: ActivatedRouteSnapshot): Observable<Number> {
    return route.params['id'];
  }
}
export const SVReportRoute: Routes = [
  {
    path: '',
    component: SVReportComponent,
    data: {
      pageTitle: 'Report',
    },
  },
  {
    path: ':id',
    component: SVDoReportComponent,
    resolve: {
      reportId: DetailResolve,
    },
    data: {
      pageTitle: 'Report',
    },
  }
];