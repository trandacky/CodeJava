import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../app.constants';

@Injectable({ providedIn: 'root' })
export class AdminService {
  private urlUAA = SERVER_API_URL + 'services/uaa/api/admin';

  constructor(private http: HttpClient) { }

  createRole(role: String): Observable<{}> {
    const roleForm = new FormData();
    roleForm.append('authorities', role.toString());
    return this.http.post(this.urlUAA + '/create-authority', roleForm);
  }
  getAllRole(): Observable<{}> {
    return this.http.get(this.urlUAA + '/get-all-authority');
  }
  deleteRole(authorities: String): Observable<{}> {
    return this.http.delete(this.urlUAA + '/delete-authority/' + authorities);
  }
  addNewDepartment(department: FormData): Observable<{}> {
    return this.http.post(this.urlUAA + '/add-department-by-name', department);
  }
  addNewCourse(course: FormData): Observable<{}> {
    return this.http.post(this.urlUAA + '/create-course', course);
  }
  createClass(classData: FormData): Observable<{}> {
    return this.http.post(this.urlUAA + '/add-class', classData);
  }
  createCourseAndDepartment(courseUUID: String, departmentId: String): Observable<any> {
    const formData = new FormData();
    formData.append('courseId', courseUUID.toString());
    formData.append('departmentId', departmentId.toString())
    return this.http.post(this.urlUAA + '/create-course-and-department', formData);
  }
  getDepartment(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + '/get-paging-department?page=' + page);
  }
  getDepartmentByCourseId(page: Number, uuid: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-department-by-course?page=' + page + '&uuid=' + uuid);
  }
  getCourseByDepartmentId(page: Number, uuid: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-course-by-department?page=' + page + '&uuid=' + uuid);
  }
  getCourse(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + '/get-paging-course?page=' + page);
  }
  getCourseAndDepartment(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + '/get-paging-course-and-department?page=' + page);
  }
  getAccountByClassId(page: Number, classUUID:String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-account-by-class?page=' 
    + page+'&uuid='+classUUID);
  }
  getClassByCADId(uuid: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-class-by-cad-id?uuid=' + uuid);
  }
  updateCourseEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-enable-course', formData);
  }
  updateAccountEnable(username: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('username', username.toString());
    formData.append('activated', enable.toString())
    return this.http.put(this.urlUAA + '/account', formData);
  }
  updateCourse(uuid: String, course: String): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('course', course.toString())
    return this.http.put(this.urlUAA + '/update-course', formData);
  }
  updateClassName(formData:FormData): Observable<any> {
    return this.http.put(this.urlUAA + '/update-class-name', formData);
  }
  updateDepartmentEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-enable-department', formData);
  }
  updateCourseAndDepartmentEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-course-and-department-enable', formData);
  }
  updateClassEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-class-enable', formData);
  }
  updateDepartment(uuid: String, departmentName: String): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('departmentName', departmentName.toString())
    return this.http.put(this.urlUAA + '/update-department', formData);
  }
  deleteCourseAndDepartment(uuid:String):Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-course-and-department/'+uuid);
  }
  deleteClass(uuid:String):Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-class-by-id/'+uuid);
  }
  deleteAccountRole(username:String):Observable<any> {
    return this.http.delete(this.urlUAA + '/account?username='+username);
  }
}
