import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

import { KhoaService } from '../khoa-service.service';

@Component({
    selector: 'jhi-account-class',
    templateUrl: './account-class.component.html',
})
export class AccountClassComponent implements OnInit {
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;
    accountList: any;
    class: any;
    classUUID: any;
    newAccount!:FormGroup;
    constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private khoaService: KhoaService) { }

    ngOnInit(): void {
        this.route.data.subscribe(({ classUUID }) => { this.classUUID = classUUID; });
        this.loadAll(this.page);
        this.newAccount= this.structureFormnewAccount();
    }
    private structureFormnewAccount(): FormGroup {

        return this.formBuilder.group
            (
                {
                    classuuid:[this.classUUID,Validators.required],
                    username: ['', [Validators.required,
                    Validators.minLength(1),
                    Validators.maxLength(50),
                    Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
                    ],],
                    password: ['', [
                        Validators.required,
                        Validators.minLength(6),
                        Validators.maxLength(50),
                        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
                    ]],
                    email: ['', [
                        Validators.required,
                        Validators.minLength(5), Validators.maxLength(254),
                        Validators.email]],
                    firstName:[''],
                    lastName:[''],
                    phoneNumber:['',[Validators.pattern('[- +()0-9]+'),Validators.maxLength(14)]]
                }
            )
    }

    private loadAll(page: number): void {
        this.khoaService.getAccountByClassId(--page, this.classUUID).subscribe(data => {
            this.accountList = data.accountList;
            this.class = data.class;
        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    updateEnable(username: String, enable: boolean): void {
        this.khoaService.updateAccountEnable(username, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
    createAccount(): void {
        this.khoaService.createAccount(this.newAccount.value).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
    delete(username: String): void {
        this.khoaService.deleteAccountRole(username).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }

}