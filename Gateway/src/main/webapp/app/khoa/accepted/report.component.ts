import { Location } from '@angular/common';
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
    constructor(private location: Location,notifier: NotifierService, private route: ActivatedRoute, private formBuilder: FormBuilder,
        private khoaService: KhoaService) {
        this.notifier = notifier;
    }

    ngOnInit(): void {
        this.route.data.subscribe(({ reportId }) => { this.reportId = reportId; });
        this.loadAll();
    }
    public back():void{
        this.location.back();
    }
    private loadAll(): void {
        this.khoaService.getDetailReport(this.reportId).subscribe(data => {
            setTimeout(() => {
                this.detailReports = data.detailReports; this.report = data.report
            }, 500)

        })
        this.khoaService.getInfoAccount().subscribe(data => {
            this.infoAccount = data;
        })
    }
   
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateAccepted3(id:number,accepted3:boolean,temp: number): void {
        this.khoaService.updateAccepted3(id,accepted3).subscribe(
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
                    temp++; this.updateAccepted3(id,accepted3,temp);
                }
            }
        );
    }
    onKeyScore(id: Number, event: any, temp: number, maxScore: Number): void {
        let inputValue = event.target.value;
        if ((inputValue <= maxScore && maxScore >= 0) || (inputValue <= 0 && maxScore === 0)) {
            if (!inputValue) {
                inputValue = 0;
            }
            this.khoaService.updateDetailScore3(id, inputValue).subscribe(
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
    updateTotalScore3(temp: number): void {
        this.khoaService.updateTotalScore3(this.reportId).subscribe(
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
                    temp++; this.updateTotalScore3(temp);
                }
            }
        );
    }
}