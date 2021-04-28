import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-department-detail-management',
    templateUrl: './department-detail.component.html',
})
export class DepartmentDetailComponent implements OnInit {
    courseUUID: any| null = null;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    course!: any;
    departments: any;
    constructor(private route: ActivatedRoute, private adminService: AdminService) { }

    ngOnInit(): void {
        this.route.data.subscribe(({ courseUUID }) => (this.courseUUID = courseUUID));
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getDepartmentByCourseId(--page,this.courseUUID).subscribe(data => {
            this.course=data.course;
            this.departments = data.departments; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.adminService.updateDepartmentEnable(uuid, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
}