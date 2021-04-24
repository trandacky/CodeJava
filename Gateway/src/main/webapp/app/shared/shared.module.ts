import { NgModule } from '@angular/core';
import { GatewaySharedLibsModule } from './shared-libs.module';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import {DemoMaterialModule} from './material';
@NgModule({
  imports: [GatewaySharedLibsModule,DemoMaterialModule],
  declarations: [AlertComponent, AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [LoginModalComponent],
  exports: [GatewaySharedLibsModule, AlertComponent, 
    AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective,DemoMaterialModule],
})
export class GatewaySharedModule {}
