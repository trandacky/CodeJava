import { Component, OnInit } from '@angular/core';
import { GiangVienService } from '../giangvien.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NotifierService } from 'angular-notifier';
@Component({
    selector: 'jhi-list-report-gvhd',
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
    constructor(notifier: NotifierService,private giangVienService: GiangVienService) { 
        this.notifier = notifier;
    }
    ngOnInit(): void {
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.giangVienService.getAllReport(--page).subscribe(data => {
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
    updateAllAccepted2(temp: number): void {
        this.giangVienService.updateAllAccepted2().subscribe(
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
                    temp++; this.updateAllAccepted2(temp);
                }
            }
        );
    }
    updateAccepted2(id:number,accepted2:boolean,temp: number): void {
        this.giangVienService.updateAccepted2(id,accepted2).subscribe(
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
                    temp++; this.updateAccepted2(id,accepted2,temp);
                }
            }
        );
    }
}

