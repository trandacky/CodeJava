import { Route } from '@angular/router';

import { ReportComponent } from './report.component';

export const ReportRoute: Route = {
  path: '',
  component: ReportComponent,
  data: {
    pageTitle: 'Report manager',
  },
};