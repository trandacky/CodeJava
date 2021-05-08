import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { SinhVienService } from '../sinhvien.service';

@Component({
    selector: 'jhi-sv-do-report',
    templateUrl: './sv-do-report.component.html',
    styleUrls: ['./sv-do-report.component.scss']
})
export class SVDoReportComponent implements OnInit {
    private notifier: NotifierService;
    reportId: any;
    loading = false;
    infoAccount: any;
    detailReports: any
    detailReports2: any
    report: any;
    constructor(notifier: NotifierService, private route: ActivatedRoute, private formBuilder: FormBuilder,
        private sinhVienService: SinhVienService) {
        this.notifier = notifier;
    }

    ngOnInit(): void {
        this.route.data.subscribe(({ reportId }) => { this.reportId = reportId; });
        this.loadAll();
    }
    private loadAll(): void {
        this.sinhVienService.getDetailReport(this.reportId).subscribe(data => {
            setTimeout(() => {
                this.detailReports = data.detailReports; this.report = data.report
            }, 1000)

        })
        this.sinhVienService.getInfoAccount().subscribe(data => {
            this.infoAccount = data;
        })
    }
    onKeyScore(id: Number, note: any, event: any, temp: number, maxScore: Number): void {
        let inputValue = event.target.value;
        if ((inputValue <= maxScore && maxScore >= 0) || (inputValue < 0 && maxScore === 0)) {
            if (!note) {
                note = '';
            }
            if (!inputValue) {
                inputValue = 0;
            }
            this.sinhVienService.updateDetailReportScore1(id, inputValue, note).subscribe(
                () => { this.showNotification('success', 'Saved success'); temp = 100; },
                () => {
                    if (temp < 10) {
                        if (temp === 9) { this.showNotification('error', 'Server not response'); }
                        temp++; this.onKeyScore(id, note, event, temp, maxScore);
                    }
                }
            );
        }
        else {
            this.showNotification('error', 'Data not correct fomat');
        }

    }
    onKeyNote(id: Number, score1: any, note: any, temp: number, maxScore: number): void {
        let inputValue = note.target.value;
        if (!score1) {
            score1 = 0;
        }
        if ((inputValue <= maxScore && maxScore >= 0) || (inputValue < 0 && maxScore === 0)) {
            score1 = 0;
        }
        if (!inputValue) {
            inputValue = '';
        }
        this.sinhVienService.updateDetailReportScore1(id, score1, inputValue).subscribe(
            () => {

                this.showNotification('success', 'Saved success'); temp = 100;
            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                    }
                    temp++; this.onKeyNote(id, score1, note, temp, maxScore);
                }
            }
        );
    }
    public showNotification(type: string, message: string): void {
        this.notifier.notify(type, message);
    }
    updateTotalScore(temp: number): void {
        this.loading = true;
        this.sinhVienService.updateTotalScore(this.reportId).subscribe(
            () => {
                this.loadAll();
                setTimeout(() => {
                    this.showNotification('success', 'Saved success'); temp = 100;
                    this.loading = false;
                }, 1500)

            },
            () => {
                if (temp < 10) {
                    if (temp === 9) {
                        this.showNotification('error', 'Server not response');
                        this.loading = false;
                    }
                    temp++; this.updateTotalScore(temp);
                }
            }
        );
    }
}