import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-course-management',
    templateUrl: './course.component.html',
})
export class CourseComponent implements OnInit {
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    courses: any;
    addForm: FormGroup = this.fb.group({
        course: ['', Validators.required],
    });
    updateForm: FormGroup = this.fb.group({
        uuid: ['', Validators.required],
        course: ['', Validators.required],
    });
    constructor(private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.addForm;
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getCourse(--page).subscribe(data => {
            this.courses = data.courses; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    editClick(uuid: String, course: String): void {
        this.edit = true;
        this.updateForm.get("uuid")?.setValue(uuid);
        this.updateForm.get("course")?.setValue(course);
    }
    update(): void {
        this.adminService.updateCourse(this.updateForm.value.uuid,
            this.updateForm.value.course).subscribe
            (
                () => {
                    this.edit = false;
                    this.updateForm.get("uuid")?.setValue('');
                    this.updateForm.get("course")?.setValue('');
                    this.loadAll(this.page);
                },
                () => { alert("update error") }
            )

    }
    cancel(): void {
        this.updateForm.get("uuid")?.setValue('');
        this.updateForm.get("course")?.setValue('');
        this.edit = false;
    }
    addNew(): void {
        this.adminService.addNewCourse(this.addForm.value).subscribe(
            () => {
                alert("add success"); this.loadAll(this.page);
                this.addForm.get("course")?.setValue('');
            },
            () => alert("add error")
        );
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.adminService.updateCourseEnable(uuid, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
}