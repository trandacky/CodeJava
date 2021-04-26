import { Route } from '@angular/router';

import { ClassComponent } from './class.component';

export const ClassRoute: Route = {
  path: '',
  component: ClassComponent,
  data: {
    pageTitle: 'Class manager',
  },
};