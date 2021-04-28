import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TypeReport } from './type-report'
@Component({
    selector: 'jhi-app-type-report',
    templateUrl: './type-report.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class TypeReportComponent implements OnInit {
    typeReports: TypeReport[] | null = null;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    formUpdateTypeReport!: FormGroup;
    formCreateTypeReport!: FormGroup;
    constructor(private formBuilder: FormBuilder, private typeReportService: KhoaService) { }
    ngOnInit(): void {
        this.formCreateTypeReport=this.functionFormCreateTypeReport();
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.typeReportService.getAllTypeReport(--page).subscribe(data => {
            this.typeReports = data.reports; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public updateStatus(id: any, enable: any): void {
        this.typeReportService.updateTypeReportEnable(id, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("update error!"); });
    }
    functionFormUpdateTypeReport(): FormGroup {
        return this.formBuilder.group
            (
                {
                    id: ['', Validators.required],
                    typeName: ['', Validators.compose([Validators.required])],
                    enable: [false, Validators.required]
                }
            )
    }
    functionFormCreateTypeReport(): FormGroup {
        return this.formBuilder.group
            (
                {
                    typeName: ['', Validators.required]
                },
            )
    }
    public submitFormUpdateTypeReport(): void {
        this.typeReportService.updateTypeReport(this.formUpdateTypeReport.value).subscribe(
            () => { this.loadAll(this.page); alert("update success!"); },
            () => { alert("update error!"); }
        );
        this.edit = false;
    }
    public submitFormCreateTypeReport(): void {
        this.typeReportService.createTypeReport(this.formCreateTypeReport.value.typeName).subscribe(
            () => { this.loadAll(this.page); alert("create success!"); this.loadAll(this.page);},
            () => { alert("create error!"); }
        );
    }
    public clickEdit(id: any, typeName: any, enable: any): void {
        this.edit = true;
        this.formUpdateTypeReport = this.functionFormUpdateTypeReport();
        this.formUpdateTypeReport = this.formBuilder.group
            (
                {
                    id: [id, Validators.required],
                    typeName: [typeName, Validators.required],
                    enable: [enable, Validators.required]
                }
            )
    }
    
    public clickCancelEdit(): void {
        this.edit = false;
    }
}

