import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
        RouterModule.forChild([
            {
                path: 'report',
                loadChildren: () => import('./list-report/report2.module').then(m => m.Report2Module),
            },
            /* jhipster-needle-add-admin-route - JHipster will add admin routes here */
        ]),
    ],
})
export class CanBoLopRoutingModule { }
