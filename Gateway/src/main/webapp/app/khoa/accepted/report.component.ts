import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { KhoaService } from '../khoa-service.service';

@Component({
    selector: 'jhi-report-accepted3',
    templateUrl: './report.component.html',
    // styleUrls: ['./sv-do-report.component.scss']
})
export class ReportComponent implements OnInit {
    private notifier: NotifierService;
    reportId: any;
    infoAccount: any;
    detailReports: any
    detailReports2: any
    report: any;
    constructor(notifier: NotifierService, private route: ActivatedRoute, private formBuilder: FormBuilder,
        private khoaService: KhoaService) {
        this.notifier = notifier;
    }

    ngOnInit(): void {
        this.route.data.subscribe(({ reportId }) => { this.reportId = reportId; });
        this.loadAll();
    }
    private loadAll(): void {
        this.khoaService.getDetailReport(this.reportId).subscribe(data => {
            setTimeout(() => {
                this.detailReports = data.detailReports; this.report = data.report
            }, 1000)

        })
        this.khoaService.getInfoAccount().subscribe(data => {
            this.infoAccount = data;
        })
    }
   
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateAccepted3(id:number,accepted2:boolean,temp: number): void {
        this.giangVienService.updateAccepted3(id,accepted2).subscribe(
            () => {
                this.loadAll();
                setTimeout(() => {
                    this.showNotification('success', 'Saved success'); temp = 100;
                }, 500)

            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                    }
                    temp++; this.updateAccepted3(id,accepted2,temp);
                }
            }
        );
    }
}