import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import {Class} from './class'
@Component({
    selector: 'jhi-class-component',
    templateUrl: './class.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class ClassComponent implements OnInit {
    classList: Class[] | null = null;
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;

    constructor(private formBuilder: FormBuilder, private khoaService: KhoaService) { }
    ngOnInit(): void {
        this.loadAll(this.page)
    }
    private loadAll(page: number): void {
        this.khoaService.getClass(--page).subscribe(data => {
            this.classList = data.classList; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public updateStatus(uuid:any,enable:boolean) :void
    {

    }

}