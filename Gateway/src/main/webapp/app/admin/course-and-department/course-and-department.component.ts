import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-course-and-department-management',
    templateUrl: './course-and-department.component.html',
})
export class CourseAndDepartmentComponent implements OnInit {
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;
    courseAndDepartments: any;
    addForm: FormGroup = this.fb.group({
        courseuuid: ['', Validators.required],
        departmentuuid: ['', Validators.required],
    });
    constructor(private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.addForm;
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getCourseAndDepartment(--page).subscribe(data => {
            this.courseAndDepartments = data.courseAndDepartment; this.totalItems = data.totalItems;

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