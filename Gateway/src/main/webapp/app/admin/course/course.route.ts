import { Route } from '@angular/router';

import { CourseComponent } from './course.component';

export const CourseRoute: Route = {
  path: '',
  component: CourseComponent,
  data: {
    pageTitle: 'Course manager',
  },
};
