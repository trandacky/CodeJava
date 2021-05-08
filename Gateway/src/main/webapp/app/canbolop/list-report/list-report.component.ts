import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CanBoLopService } from '../canbolop.service';

@Component({
    selector: 'jhi-list-report2',
    templateUrl: './list-report.component.html',
    // styleUrls: ['./can-bo-lop.component.scss']
})
export class ListReportComponent implements OnInit {
    private notifier: NotifierService;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    reports: any;
    constructor(notifier: NotifierService, private route: ActivatedRoute, private formBuilder: FormBuilder,
        private canBoLopService: CanBoLopService) {
        this.notifier = notifier;
    }

    ngOnInit(): void {
        this.loadAll(this.page);
    }
    private loadAll(page:number): void {
        this.canBoLopService.getAllReport(--page).subscribe(data => {
                this.reports = data.reports;
                this.totalItems = data.totalItems
            })
    }
    public transition(): void {
        this.loadAll(this.page);
    }
}