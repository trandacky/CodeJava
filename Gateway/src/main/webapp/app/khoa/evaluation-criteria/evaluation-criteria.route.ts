import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Route } from '@angular/router';
import { Observable} from 'rxjs';

import { EvaluationCriteriaComponent } from './evaluation-criteria.component';
@Injectable({ providedIn: 'root' })
export class EvaluationCriteriaResolve implements Resolve<Number> {
  resolve(route: ActivatedRouteSnapshot): Observable<Number> {
    return route.params['id'];
  }
}
export const EvaluationCriteriaRoute: Route =
{
  path: ':id/edit',
  component: EvaluationCriteriaComponent,
  resolve: {
    idTypeReport: EvaluationCriteriaResolve,
  },
}
