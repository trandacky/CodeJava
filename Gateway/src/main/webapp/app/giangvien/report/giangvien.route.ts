import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { Observable } from 'rxjs';

import { ReportComponent } from './report.component';
import { ListReportComponent } from './list-report.component';

@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<Number> {
  resolve(route: ActivatedRouteSnapshot): Observable<Number> {
    return route.params['id'];
  }
}
export const GiangVienRoute: Routes = [
  {
    path: '',
    component: ListReportComponent,
    data: {
      pageTitle: 'Report',
    },
  },
  {
    path: ':id',
    component: ReportComponent,
    resolve: {
      reportId: DetailResolve,
    },
    data: {
      pageTitle: 'Report',
    },
  }
];