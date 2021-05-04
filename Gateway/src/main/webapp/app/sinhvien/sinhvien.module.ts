import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
        RouterModule.forChild([
            {
                path: 'report',
                loadChildren: () => import('./svreport/svreport.module').then(m => m.SVReportModule),
            },
            /* jhipster-needle-add-admin-route - JHipster will add admin routes here */
        ]),
    ],
})
export class SinhVienRoutingModule { }
