import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
 
@NgModule({
    imports: [
      /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
      RouterModule.forChild([
        {
            path: 'type-report',
            loadChildren: () => import('./type-report/type-report.module').then(m => m.TypeReportModule),
          },
          {
            path: 'evaluation-criteria',
            loadChildren: () => import('./evaluation-criteria/evaluation-criteria.module').then(m => m.EvaluationCriteriaModule),
          },
          {
            path: 'class',
            loadChildren: () => import('./class/class.module').then(m => m.ClassModule),
          },
        /* jhipster-needle-add-admin-route - JHipster will add admin routes here */
      ]),
    ],
  })
  export class KhoaRoutingModule {}
  