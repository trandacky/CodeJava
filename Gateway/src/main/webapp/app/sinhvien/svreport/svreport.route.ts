import { Route } from '@angular/router';

import { SVReportComponent } from './svreport.component';

export const SVReportRoute: Route = {
  path: '',
  component: SVReportComponent,
  data: {
    pageTitle: 'Report',
  },
};