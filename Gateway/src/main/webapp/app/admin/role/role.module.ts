import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

import { RoleComponent } from './role.component';

import { roleRoute } from './role.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild([roleRoute])],
  declarations: [RoleComponent],
})
export class RoleModule {}