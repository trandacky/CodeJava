import { Component, OnInit } from '@angular/core';
import { FormBuilder} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-account-department-management',
    templateUrl: './account-department.component.html',
})
export class AccountDepartmentComponent implements OnInit {
    departmentUUID!:any;
    department!:any;
    dropdown:any;
    accounts: any;
    username!:String;
   
    constructor(private route: ActivatedRoute,private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.route.data.subscribe(({ departmentUUID }) => (this.departmentUUID = departmentUUID));
        this.loadAll();
        this.loadDropDown();
        this.loadDepartment();
    }
    private loadAll(): void {
        this.adminService.getAllAccountKhoa(this.departmentUUID).subscribe(data => {
            this.accounts = data;
        });
    }
    private loadDropDown(): void {
        this.adminService.getAccountKhoaNot().subscribe(data => {
            this.dropdown=data;
        });
    }
    private loadDepartment(): void {
        this.adminService.getDepartmentById(this.departmentUUID).subscribe(data => {
            this.department=data;
        });
    }
    createAccountDepartment():void
    {   if(this.username){
        this.adminService.createAccountDepartment(this.username,this.departmentUUID).subscribe(
            () => {this.loadAll(); this.loadDropDown(); },
            () => { alert("server error"); }
        );
    }
    }
    delete(username:String):void
    {
        this.adminService.deleteAccountDepartment(username,this.departmentUUID).subscribe(
            () => {this.loadAll(); this.loadDropDown(); },
            () => { alert("server error"); }
        );
    }

    
}