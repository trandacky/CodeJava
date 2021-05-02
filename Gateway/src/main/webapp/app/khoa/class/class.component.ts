import { Component, OnInit } from '@angular/core';
import { KhoaService } from '../khoa-service.service';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
@Component({
    selector: 'jhi-class-component',
    templateUrl: './class.component.html',
    // styleUrls: ['./type-report.component.scss']
})
export class ClassComponent implements OnInit {
    
    courseAndDepartmentUUID!: String;
    totalItems = 0;
    page = 1;
    edit=false;
    itemsPerPage = ITEMS_PER_PAGE;
    classList: any;
    courseAndDepartment: any;
    addForm: FormGroup = this.fb.group({
        courseAndDepartmentId: [this.courseAndDepartmentUUID, Validators.required],
        className: ['', Validators.required],
    });
    editForm!: FormGroup;
    constructor(private route: ActivatedRoute,private fb: FormBuilder, private khoaService: KhoaService) { }

    ngOnInit(): void {
        
        this.route.data.subscribe(({ courseAndDepartmentUUID }) => 
        {this.courseAndDepartmentUUID = courseAndDepartmentUUID;
        this.addForm.get('courseAndDepartmentId')?.setValue(courseAndDepartmentUUID);
        });
        
        this.addForm;
        this.loadAll();
    }
    private loadAll(): void {
        this.khoaService.getClassByCADId(this.courseAndDepartmentUUID).subscribe(data => {
            this.classList=data.classList;
            this.courseAndDepartment=data.courseAndDepartment;
        });
    }
    updateEnable(uuid: any, enable: boolean): void {
        this.khoaService.updateClassEnable(uuid, enable).subscribe(
            () => { this.loadAll(); },
            () => { alert("error"); }
        );
    }
    delete(uuid:String):void{
        this.khoaService.deleteClass(uuid).subscribe(
            () => { this.loadAll(); },
            () => { alert("server error or class have account"); }
        );
    }
    submitCreate():void{
        this.khoaService.createClass(this.addForm.value).subscribe(
            () => { alert("success");this.loadAll();  this.addForm.get('className')?.setValue('');},
            () => { alert("server error"); }
        );
    }
    editClick(uuid:String, name:String):void{
        this.edit=true;
        this.editForm=this.fb.group(
        {
            uuid: [uuid, Validators.required],
            className: [name, Validators.required],
        }
        )
    }
    submitEdit():void{
        this.khoaService.updateClassName(this.editForm.value).subscribe(
            () => { alert("success");this.loadAll(); this.edit=false;},
            () => { alert("server error"); }
        );
        
    }
    cancel():void{
       this.edit=false;
    }
}