import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-course-and-department-detail-management',
    templateUrl: './detail.component.html',
})
export class CourseAndDepartmentDetailComponent implements OnInit {
    courseAndDepartmentUUID!: String;
    totalItems = 0;
    page = 1;
    itemsPerPage = ITEMS_PER_PAGE;
    classList: any;
    courseAndDepartment: any;
    addForm: FormGroup = this.fb.group({
        courseuuid: ['', Validators.required],
        departmentuuid: ['', Validators.required],
    });
    constructor(private route: ActivatedRoute,private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.addForm;
        this.route.data.subscribe(({ courseAndDepartmentUUID }) => 
        (this.courseAndDepartmentUUID = courseAndDepartmentUUID));
        this.loadAll();
    }
    private loadAll(): void {
        this.adminService.getClassByCADId(this.courseAndDepartmentUUID).subscribe(data => {
            this.classList=data.classList;
            
            this.courseAndDepartment=data.courseAndDepartment;
        });
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.adminService.updateCourseAndDepartmentEnable(uuid, enable).subscribe(
            () => { this.loadAll(); },
            () => { alert("error"); }
        );
    }
    delete(uuid:String):void{
        alert(uuid);
        this.adminService.deleteClass(uuid).subscribe(
            () => { this.loadAll(); },
            () => { alert("server error or class have account"); }
        );
    }
}