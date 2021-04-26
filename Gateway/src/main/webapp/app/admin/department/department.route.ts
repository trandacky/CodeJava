import { Route } from '@angular/router';

import { DepartmentComponent } from './department.component';

export const departmentRoute: Route = {
  path: '',
  component: DepartmentComponent,
  data: {
    pageTitle: 'Department manager',
  },
};
