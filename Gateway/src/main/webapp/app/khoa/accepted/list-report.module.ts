import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GatewaySharedModule } from 'app/shared/shared.module';

// import { SVReportComponent } from './report.component';
import { ListReportComponent } from './list-report.component';
import { ReportComponent } from './report.component';

import { AcceptedRoute } from './accepted.route';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
const customNotifierOptions: NotifierOptions = {
  position: {
		horizontal: {
			position: 'left',
			distance: 12
		},
		vertical: {
			position: 'bottom',
			distance: 12,
			gap: 10
		}
	},
  theme: 'material',
  behaviour: {
    autoHide: 3000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 1
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 0,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 0,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 0,
      easing: 'ease'
    },
    overlap: 10
  }
};

@NgModule({
  imports: [NotifierModule.withConfig(customNotifierOptions),GatewaySharedModule, RouterModule.forChild(AcceptedRoute)],
  declarations: [ListReportComponent,ReportComponent],
})
export class AcceptedReportModule {} 