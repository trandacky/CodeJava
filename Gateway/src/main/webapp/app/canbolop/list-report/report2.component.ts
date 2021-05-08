import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { CanBoLopService } from '../canbolop.service';

@Component({
    selector: 'jhi-report2',
    templateUrl: './report2.component.html',
    styleUrls: ['./report2.component.scss']
})
export class Report2Component implements OnInit {
    private notifier: NotifierService;
    reportId: any;
    loading = false;
    infoAccount: any;
    detailReports: any
    report: any;
    username: any;
    constructor(notifier: NotifierService, private route: ActivatedRoute, private formBuilder: FormBuilder,
        private canBoLopService: CanBoLopService) {
        this.notifier = notifier;
    }

    ngOnInit(): void {
        this.route.data.subscribe(({ reportId }) => { this.reportId = reportId; });
        this.loadAll();
    }
    private loadAll(): void {
        this.canBoLopService.getDetailReport(this.reportId).subscribe(data => {
            this.detailReports = data.detailReports; this.report = data.report;
            this.infoAccount = data.infoAccount;
        })
    }
    onKeyScore(id: Number, event: any, temp: number, maxScore: Number): void {
        let inputValue = event.target.value;
        if ((inputValue <= maxScore && maxScore >= 0) || (inputValue <= 0 && maxScore === 0)) {
            if (!inputValue) {
                inputValue = 0;
            }
            this.canBoLopService.updateDetailReportScore2(id, inputValue).subscribe(
                () => { this.showNotification('success', 'Saved success'); temp = 100; },
                () => {
                    if (temp < 10) {
                        if (temp === 9) { this.showNotification('error', 'Server not response'); }
                        temp++; this.onKeyScore(id, event, temp, maxScore);
                    }
                }
            );
        }
        else {
            this.showNotification('error', 'Data not correct fomat');
        }

    }

    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateTotalScore(temp: number): void {
        this.canBoLopService.updateTotalScore2(this.reportId).subscribe(
            () => {
                this.loadAll();
                setTimeout(() => {
                    this.showNotification('success', 'Saved success'); temp = 100;
                }, 200)
            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                        temp = 100;
                    }
                    temp++; this.updateTotalScore(temp);
                }
            }
        );
    }
}