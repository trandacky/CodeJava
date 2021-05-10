import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { Observable } from 'rxjs';

import { ReportComponent } from './report.component';
import { ListReportComponent } from './list-report.component';
import { ClassListComponent } from './class-list.component';

@Injectable({ providedIn: 'root' })
export class DetailResolve implements Resolve<Number> {
  resolve(route: ActivatedRouteSnapshot): Observable<Number> {
    return route.params['id'];
  }
}
@Injectable({ providedIn: 'root' })
export class DetailResolve2 implements Resolve<any> {
  resolve(route: ActivatedRouteSnapshot): Observable<any> {
    return route.params['id'];
  }
}
export const AcceptedRoute: Routes = [
  {
    path: '',
    component: ClassListComponent,
    data: {
      pageTitle: 'Report',
    },
  },
  {
    path: 'class/:id',
    component: ReportComponent,
    resolve: {
      reportId: DetailResolve,
    },
    data: {
      pageTitle: 'Report',
    },
  },
  {
    path: 'class',
    component: ListReportComponent,
    data: {
      pageTitle: 'Report',
    },
  }
];