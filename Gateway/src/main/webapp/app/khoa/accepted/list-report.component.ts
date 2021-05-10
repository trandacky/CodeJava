import { Component, OnInit } from '@angular/core';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NotifierService } from 'angular-notifier';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
@Component({
    selector: 'jhi-list-report-accepted3',
    templateUrl: './list-report.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class ListReportComponent implements OnInit {
    private notifier: NotifierService;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    reports: any;
    conditionRequest: any;
    condition!: FormGroup;
    constructor(private location: Location,private formBuilder: FormBuilder,private router:ActivatedRoute, notifier: NotifierService, private khoaService: KhoaService) {
        this.notifier = notifier;
    }
    ngOnInit(): void {
        this.router.queryParams.subscribe(param=>{this.conditionRequest=param})
        this.condition=this.addCondition();
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.condition.get('page')?.setValue(--page);
        // this.condition.get('year')?.setValue(this.conditionRequest.year);
        // this.condition.get('semester')?.setValue(this.conditionRequest.semester);
        // this.condition.get('classUUID')?.setValue(this.conditionRequest.classUUID);
        // this.condition.get('typeReportId')?.setValue(this.conditionRequest.typeReportId);
        this.khoaService.getAllReport(this.condition.value).subscribe(data => {
            this.reports = data.reports;
            this.totalItems = data.totalItems;
        });
    }
    
    addCondition(): FormGroup {
        return this.formBuilder.group({
            page: [0, Validators.required],
            year: [this.conditionRequest.year, Validators.required],
            semester: [this.conditionRequest.semester, Validators.required],
            classUUID: [this.conditionRequest.classUUID],
            typeReportId:[this.conditionRequest.typeReportId]
        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateAllAccepted3(temp: number): void {
        this.khoaService.updateAllAccepted3(this.condition.value).subscribe(
            () => {
                this.loadAll(this.page);
                setTimeout(() => {
                    this.showNotification('success', 'Saved success'); temp = 100;
                }, 500)

            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                    }
                    temp++; this.updateAllAccepted3(temp);
                }
            }
        );
    }
    public back():void{
        this.location.back();
    }
    updateAccepted3(id: number, accepted3: boolean, temp: number): void {
        this.khoaService.updateAccepted3(id, accepted3).subscribe(
            () => {
                this.loadAll(this.page);
                setTimeout(() => {
                    this.showNotification('success', 'Saved success'); temp = 100;
                }, 500)

            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                    }
                    temp++; this.updateAccepted3(id, accepted3, temp);
                }
            }
        );
    }
}

