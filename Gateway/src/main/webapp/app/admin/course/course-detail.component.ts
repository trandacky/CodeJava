import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-course-detail-management',
    templateUrl: './course-detail.component.html',
})
export class CourseDetailComponent implements OnInit {
    departmentUUID: any| null = null;
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    courses!: any;
    department: any;
    constructor(private route: ActivatedRoute, private adminService: AdminService) { }

    ngOnInit(): void {
        this.route.data.subscribe(({ departmentUUID }) => (this.departmentUUID = departmentUUID));
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getCourseByDepartmentId(--page,this.departmentUUID).subscribe(data => {
            this.department=data.department;
            this.courses = data.courses; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.adminService.updateCourseAndDepartmentEnable(uuid, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
    delete(uuid:String): void{
        this.adminService.deleteCourseAndDepartment(uuid).subscribe(
            () => { alert("deleted success");this.loadAll(this.page); },
            () => { alert("server error or department haved class"); }
        );
    }
}