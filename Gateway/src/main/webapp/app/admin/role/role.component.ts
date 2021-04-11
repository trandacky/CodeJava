import { Component, OnInit } from '@angular/core';
import { FormBuilder,Validators } from '@angular/forms';
import { AdminService } from '../service-admin.service';

@Component({
  selector: 'jhi-role-management',
  templateUrl: './role.component.html',
})
export class RoleComponent implements OnInit {
  roles: any;
  addForm = this.fb.group({
    authorities: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, private serviceAdmin: AdminService) { }

  ngOnInit(): void {
    this.getAllRole();
  }
  getAllRole(): void {
    this.addForm.setValue({authorities:''});
    this.serviceAdmin.getAllRole().subscribe(
      data => this.roles = data)
  }
  addNewRole():void {
    this.serviceAdmin.createRole(this.addForm.value.authorities).subscribe( 
    () => {alert("add success");this.getAllRole();},
    () => alert("add error"));
    
  }
  deleteRole(authorities: String): void
  {
    this.serviceAdmin.deleteRole(authorities).subscribe( 
      () => {alert("delete success");this.getAllRole();},
      () => alert("delete error"));
  }
}