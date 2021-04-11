import { Component} from '@angular/core';
import { RegisterService } from './register.service';

@Component({
  templateUrl: './register-admin.component.html',
})
export class RegisterAdminComponent{
  constructor(private registerService: RegisterService) {}
  login(): void
  {
    this.registerService.createAdmin().subscribe(data=> alert(data));
  }
}
