import { Route } from '@angular/router';

import { TypeReportComponent } from './type-report.component';

export const TypeReportRoute: Route = {
  path: '',
  component: TypeReportComponent,
  data: {
    pageTitle: 'Type Report manager',
  },
};