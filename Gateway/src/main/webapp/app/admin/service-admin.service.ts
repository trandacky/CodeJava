import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../app.constants';

@Injectable({ providedIn: 'root' })
export class AdminService {
  private urlUAA=SERVER_API_URL + 'services/uaa/api';

  constructor(private http: HttpClient) { }

  createRole(role: String): Observable<{}> {
    const roleForm = new FormData();
    roleForm.append('authorities', role.toString());
    return this.http.post(this.urlUAA+'/admin/create-authority', roleForm);
  }
  getAllRole(): Observable<{}> {
    return this.http.get(this.urlUAA+'/admin/get-all-authority');
  }
  deleteRole(authorities: String): Observable<{}> {
    return this.http.delete(this.urlUAA+'/admin/delete-authority/'+authorities);
  }
  addNewDepartment(department: FormData): Observable<{}> {
    return this.http.post(this.urlUAA+'/admin/add-department-by-name',department);
  }
  addNewCourse(course: FormData): Observable<{}> {
    return this.http.post(this.urlUAA+'/admin/create-course',course);
  }
  getDepartment(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + '/admin/get-paging-department?page='+page);
  }
  getCourse(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + '/admin/get-paging-course?page='+page);
  }
  updateCourseEnable(uuid:String, enable:boolean): Observable<any> {
    const formData= new FormData();
    formData.append('uuid',uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/admin/update-enable-course',formData);
  }
  updateCourse(uuid:String, course: String): Observable<any> {
    const formData= new FormData();
    formData.append('uuid',uuid.toString());
    formData.append('course', course.toString())
    return this.http.put(this.urlUAA + '/admin/update-course',formData);
  }
  updateDepartmentEnable(uuid:String, enable:boolean): Observable<any> {
    const formData= new FormData();
    formData.append('uuid',uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/admin/update-enable-department',formData);
  }
  updateDepartment(uuid:String, departmentName: String): Observable<any> {
    const formData= new FormData();
    formData.append('uuid',uuid.toString());
    formData.append('departmentName', departmentName.toString())
    return this.http.put(this.urlUAA + '/admin/update-department',formData);
  }
}
