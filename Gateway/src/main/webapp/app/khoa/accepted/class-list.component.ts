import { Component, OnInit } from '@angular/core';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NotifierService } from 'angular-notifier';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
    selector: 'jhi-class-list-accepted3',
    templateUrl: './class-list.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class ClassListComponent implements OnInit {
    private notifier: NotifierService;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    condition!: FormGroup
    classList: any;
    typeReports: any;
    constructor(private router:Router,private formBuilder: FormBuilder, notifier: NotifierService, private khoaService: KhoaService) {
        this.notifier = notifier;
    }
    ngOnInit(): void {
        this.condition = this.addCondition(this.page);
        this.loadAll(this.page);
        this.khoaService.getAllTypeReportEnable().subscribe(data => {
            this.typeReports = data;
        });
    }

    seach(): void {
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.condition.get("page")?.setValue(--page);
        this.khoaService.getClassReportKhoa(this.condition.value).subscribe(data => {
            this.classList = data.classList;
            this.totalItems = data.totalItems;
        });
    }
    addCondition(page: number): FormGroup {
        return this.formBuilder.group({
            page: [--page, Validators.required],
            year: [new Date().getFullYear(), Validators.required],
            semester: [1, Validators.required],
            classUUID: [''],
            typeReportId:['']
        });
    }
    transform(): any {
        const res = [];
        const year: number = new Date().getFullYear();
        for (let i = year - 5; i < year + 5; i++) {
            res.push(i);
        }
        return res;
    }
    functionCondition(): FormGroup {
        return this.formBuilder.group
            (
                {
                    id: ['', Validators.required],
                    typeName: ['', Validators.compose([Validators.required])],
                    enable: [false, Validators.required]
                }
            )
    }
    routerLink(classUUID:String):void{
        this.condition.get("classUUID")?.setValue(classUUID);
        this.router.navigate(['/khoa/accepted3/class'],  {queryParams:this.condition.value});
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
}

