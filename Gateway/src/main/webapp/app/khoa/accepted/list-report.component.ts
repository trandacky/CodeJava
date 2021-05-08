import { Component, OnInit } from '@angular/core';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NotifierService } from 'angular-notifier';
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
    constructor(notifier: NotifierService,private khoaService: KhoaService) { 
        this.notifier = notifier;
    }
    ngOnInit(): void {
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.khoaService.getAllReport(--page).subscribe(data => {
            this.reports = data.reports;
            this.totalItems = data.totalItems;
        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateAllAccepted3(temp: number): void {
        this.khoaService.updateAllAccepted3().subscribe(
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
    updateAccepted3(id:number,accepted3:boolean,temp: number): void {
        this.khoaService.updateAccepted2(id,accepted3).subscribe(
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
                    temp++; this.updateAccepted3(id,accepted3,temp);
                }
            }
        );
    }
}

