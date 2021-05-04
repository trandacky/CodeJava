import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
@Component({
    selector: 'jhi-report-manager',
    templateUrl: './report.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class ReportComponent implements OnInit {
    typeReports: any;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    classList: any;
    formCreateReport!: FormGroup;
    constructor(private formBuilder: FormBuilder, private khoaService: KhoaService) { }
    ngOnInit(): void {
        this.loadAll(this.page);
        this.formCreateReport=this.functionFormCreateReport();
    }
    private loadAll(page : number): void {
        this.khoaService.getClassByUsernameKhoa(--page).subscribe(data => {
            this.classList = data.classList;
            this.totalItems=data.totalItems;
        });
        this.khoaService.getAllTypeReportEnable().subscribe(data => {
            this.typeReports = data;
        });
        
    }
    functionFormCreateReport(): FormGroup {
        return this.formBuilder.group
            (
                {
                    classuuid: [''],
                    semester: ['', [Validators.required,Validators.pattern('[- +()0-9]+'), Validators.maxLength(14)]],
                    year: ['', [Validators.required,
                    Validators.pattern('[- +()0-9]+'), Validators.maxLength(14)]],
                    typeReportId: ['', Validators.required]
                }
            )
    }
    transform() : any {
        const res = [];
        const year: number = new Date().getFullYear();
        for (let i = year-5; i < year+5; i++) {
            res.push(i);
          }
          return res;
      }
    createAll():void{
        alert(this.formCreateReport.value.typeReportId);
    }
    createByClassUUId(id:Number):void{ 
        this.formCreateReport.get('classuuid')?.setValue(id);
        this.khoaService.createReportAndDetailbyClass(this.formCreateReport.value).subscribe(
            () => { this.loadAll(this.page); alert("success!"); },
            () => { alert("error!"); }
        );
    }
    public transition(): void {
        this.loadAll(this.page);
    }
}

