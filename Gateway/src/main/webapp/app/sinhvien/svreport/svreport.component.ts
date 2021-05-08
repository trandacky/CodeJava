import { Component, OnInit } from '@angular/core';
import { SinhVienService } from '../sinhvien.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
@Component({
    selector: 'jhi-svreport',
    templateUrl: './svreport.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class SVReportComponent implements OnInit {
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    reports: any;
    constructor(private sinhVienService: SinhVienService) { }
    ngOnInit(): void {
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.sinhVienService.getAllReport(--page).subscribe(data => {
            this.reports = data.reports;
            this.totalItems = data.totalItems;
        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }

}

