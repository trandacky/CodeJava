import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AdminService } from '../service-admin.service';

@Component({
    selector: 'jhi-department-management',
    templateUrl: './department.component.html',
})
export class DepartmentComponent implements OnInit {
    totalItems = 0;
    page = 1;
    edit = false;
    itemsPerPage = ITEMS_PER_PAGE;
    departments: any;
    addForm: FormGroup = this.fb.group({
        departmentName: ['', Validators.required],
    });
    updateForm: FormGroup = this.fb.group({
        uuid: ['', Validators.required],
        departmentName: ['', Validators.required],
    });
    constructor(private fb: FormBuilder, private adminService: AdminService) { }

    ngOnInit(): void {
        this.addForm;
        this.loadAll(this.page);
    }
    private loadAll(page: number): void {
        this.adminService.getDepartment(--page).subscribe(data => {
            this.departments = data.departments; this.totalItems = data.totalItems;

        });
    }
    public transition(): void {
        this.loadAll(this.page);
    }
    editClick(uuid: String, departmentName: String): void {
        this.edit = true;
        this.updateForm.get("uuid")?.setValue(uuid);
        this.updateForm.get("departmentName")?.setValue(departmentName);
    }
    update(): void {
        this.adminService.updateDepartment(this.updateForm.value.uuid,
            this.updateForm.value.departmentName).subscribe
            (
                () => {
                    this.edit = false;
                    this.updateForm.get("uuid")?.setValue('');
                    this.updateForm.get("departmentName")?.setValue('');
                    this.loadAll(this.page);
                },
                () => { alert("update error") }
            )

    }
    cancel(): void {
        this.updateForm.get("uuid")?.setValue('');
        this.updateForm.get("departmentName")?.setValue('');
        this.edit = false;
    }
    addNew(): void {
        this.adminService.addNewDepartment(this.addForm.value).subscribe(
            () => {
                alert("add success"); this.loadAll(this.page);
                this.addForm.get("departmentName")?.setValue('');
            },
            () => alert("add error")
        );
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.adminService.updateDepartmentEnable(uuid, enable).subscribe(
            () => { this.loadAll(this.page); },
            () => { alert("error"); }
        );
    }
}