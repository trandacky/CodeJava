import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-account-of-class-management',
    templateUrl: './account-class.component.html',
})
export class AccountClassComponent implements OnInit {
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;
    accountList:any;
    class:any;
    classUUID:any;
    constructor(private route: ActivatedRoute,private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.route.data.subscribe(({ classUUID }) => 
        {this.classUUID = classUUID;});
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getAccountByClassId(--page,this.classUUID).subscribe(data => {
            this.accountList=data.accountList;
            this.class=data.class;
        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    updateEnable(username: String, enable: boolean): void {
        this.adminService.updateAccountEnable(username, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
    delete(username: String):void {
        this.adminService.deleteAccountRole(username).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
}