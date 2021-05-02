import { Component, OnInit } from '@angular/core';
import { KhoaService } from '../khoa-service.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
@Component({
    selector: 'jhi-cad-component',
    templateUrl: './cad.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class CadComponent implements OnInit {
    courseAndDepartments: any | null = null;
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;

    constructor( private khoaService: KhoaService) { }
    ngOnInit(): void {
        this.loadAll(this.page)
    }
    private loadAll(page: number): void {
        this.khoaService.getClass(--page).subscribe(data => {
            this.courseAndDepartments = data.courseAndDepartments; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    public updateStatus(uuid:any,enable:boolean) :void
    {
        this.khoaService.updateCourseAndDepartmentEnable(uuid, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
    delete(uuid:String): void{
        this.khoaService.deleteCourseAndDepartment(uuid).subscribe(
            () => { alert("deleted success");this.loadAll(this.page); },
            () => { alert("server error or department haved class"); }
        );
    }

}