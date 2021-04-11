import { Route } from '@angular/router';

import { RoleComponent } from './role.component';

export const roleRoute: Route = {
  path: '',
  component: RoleComponent,
  data: {
    pageTitle: 'Role manager',
  },
};
