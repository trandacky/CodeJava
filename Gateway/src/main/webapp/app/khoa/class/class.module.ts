import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { CadComponent } from './cad.component';

import { ClassRoute } from './class.route';
import { ClassComponent } from './class.component';
import { AccountClassComponent } from './account-class.component';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild(ClassRoute)],
  declarations: [CadComponent,ClassComponent,AccountClassComponent],
})
export class ClassModule {} 