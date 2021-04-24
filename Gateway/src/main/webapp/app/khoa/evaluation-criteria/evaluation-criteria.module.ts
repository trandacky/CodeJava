import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { EvaluationCriteriaComponent } from './evaluation-criteria.component';

import { EvaluationCriteriaRoute } from './evaluation-criteria.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([EvaluationCriteriaRoute])],
  declarations: [EvaluationCriteriaComponent],
})
export class EvaluationCriteriaModule {} 