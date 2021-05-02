import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class KhoaService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/admin';
  private urlUAA = SERVER_API_URL+'services/uaa/api/khoa';

  constructor(private http: HttpClient) { }
  getAllTypeReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-type-report?page=${page}`);
    // return this.http.get(`${this.url}` + `/get-all-type-report${paging? paging: ''}`);
  }
  createTypeReport(typeName: string): Observable<any> {
    const typeReportParam = new FormData();
    typeReportParam.append('typename',typeName);
    return this.http.post(this.urlService+'/create-type-report', typeReportParam);
  }
  updateClassEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-class-enable', formData);
  }
  updateTypeReportEnable(id: Number, enable:Boolean): Observable<Object> {
    const typeReportForm = new FormData();
    typeReportForm.append('id',id.toString());
    typeReportForm.append('enable', enable.toString())
    return this.http.put(this.urlService+'/update-enable-type-report', typeReportForm);
  }

  updateTypeReport(typeReportForm:FormData): Observable<Object>
  {
    return this.http.put(this.urlService+'/update-type-report', typeReportForm);
  }
  getEvaluationByTypeId(id: Number): Observable<any> {
    return this.http.get(this.urlService + '/get-report-example-by-type-id/'+id);

    // return this.http.get(`${this.url}` + `/get-all-type-report${paging? paging: ''}`);
  }
  getClassByCADId(uuid: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-class-by-cad-id?uuid=' + uuid);
  }
  deleteEvaluation(id: Number):Observable<any>
  {
    return this.http.delete(`${this.urlService}/delete-evaluation-report/`+id);
  }
 
  createNewEvaluation(formEvaluationCriteria: FormData):Observable<any>
  {
    return this.http.post<any>(`${this.urlService}/create-evaluation-criteria`, formEvaluationCriteria);
  }
  createClass(classData: FormData): Observable<{}> {
    return this.http.post(this.urlUAA + '/add-class', classData);
  }
  createAccount(account: FormData): Observable<{}> {
    return this.http.post(this.urlUAA + '/create-account', account);
  }
  // updateEvaluation(formEvaluationCriteria: FormData):Observable<any>
  // {
  //   return this.http.put<any>(`${this.url}/update-evaluation-criteria`, formEvaluationCriteria);
  // }
  getClass(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + `/get-cad-by-username?page=`+page);
  }
  updateCourseAndDepartmentEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-course-and-department-enable', formData);
  }
  deleteCourseAndDepartment(uuid: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-course-and-department/' + uuid);
  }
  deleteClass(uuid: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-class-by-id/' + uuid);
  }
  updateClassName(formData: FormData): Observable<any> {
    return this.http.put(this.urlUAA + '/update-class-name', formData);
  }
  getAccountByClassId(page: Number, classUUID: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-account-by-class?page='
      + page + '&uuid=' + classUUID);
  }
  updateAccountEnable(username: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('username', username.toString());
    formData.append('activated', enable.toString())
    return this.http.put(this.urlUAA + '/account', formData);
  }
  deleteAccountRole(username: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/account?username=' + username);
  }
}

