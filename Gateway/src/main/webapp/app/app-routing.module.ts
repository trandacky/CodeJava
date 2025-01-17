import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { Authority } from 'app/shared/constants/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
        },
        {
          path: 'sinhvien',
          data: {
            authorities: [Authority.SV],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./sinhvien/sinhvien.module').then(m => m.SinhVienRoutingModule),
        }
        ,{
          path: 'khoa',
          data: {
            authorities: [Authority.KHOA],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./khoa/khoa-routing.module').then(m => m.KhoaRoutingModule),
        },
        {
          path: 'canbolop',
          data: {
            authorities: [Authority.CBL],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./canbolop/canbolop.module').then(m => m.CanBoLopRoutingModule),
        },
        {
          path: 'giangvien',
          data: {
            authorities: [Authority.GVHD],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./giangvien/giangvien.module').then(m => m.GiangVienRoutingModule),
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
        },
        ...LAYOUT_ROUTES,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class GatewayAppRoutingModule {}
