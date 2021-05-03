import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class KhoaService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/khoa';
  private urlUAA = SERVER_API_URL+'services/uaa/api/khoa';

  constructor(private http: HttpClient) { }
  getAllTypeReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-type-report?page=${page}`);
    // return this.http.get(`${this.url}` + `/get-all-type-report${paging? paging: ''}`);
  }
  getAccountByClassId(page: Number, classUUID: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-account-by-class?page='
      + page + '&uuid=' + classUUID);
  }
  getClass(page: Number): Observable<any> {
    return this.http.get(this.urlUAA + `/get-cad-by-username?page=`+page);
  }
  getEvaluationByTypeId(id: Number): Observable<any> {
    return this.http.get(this.urlService + '/get-report-example-by-type-id/'+id);

    // return this.http.get(`${this.url}` + `/get-all-type-report${paging? paging: ''}`);
  }
  getClassByCADId(uuid: String): Observable<any> {
    return this.http.get(this.urlUAA + '/get-class-by-cad-id?uuid=' + uuid);
  }
  getAllTypeReportEnable(): Observable<any> {
    return this.http.get(this.urlService + '/get-all-type-report-enable');
  }
  getClassByUsernameKhoa(page:number): Observable<any> {
    return this.http.get(this.urlUAA + '/get-class-paging-by-username?page='+page);
  }
  createReportAndDetailbyClass(data: FormData): Observable<any> {
    return this.http.post(this.urlService+
      '/create-report-and-detail-report-by-type-report-and-classuuid', data);
  }
  
  createTypeReport(typeName: string): Observable<any> {
    const typeReportParam = new FormData();
    typeReportParam.append('typename',typeName);
    return this.http.post(this.urlService+'/create-type-report', typeReportParam);
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
  
  
  
  updateClassName(formData: FormData): Observable<any> {
    return this.http.put(this.urlUAA + '/update-class-name', formData);
  }
 
  updateAccountEnable(username: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('username', username.toString());
    formData.append('activated', enable.toString())
    return this.http.put(this.urlUAA + '/account', formData);
  }
  
  updateCourseAndDepartmentEnable(uuid: String, enable: boolean): Observable<any> {
    const formData = new FormData();
    formData.append('uuid', uuid.toString());
    formData.append('enable', enable.toString())
    return this.http.put(this.urlUAA + '/update-course-and-department-enable', formData);
  }
  updateEvaluation(formEvaluationCriteria: FormData):Observable<any>
  {
    return this.http.put<any>(`${this.urlService}/update-evaluation-criteria`, formEvaluationCriteria);
  }
  deleteEvaluation(id: Number):Observable<any>
  {
    return this.http.delete(`${this.urlService}/delete-evaluation-report/`+id);
  }
 
  deleteCourseAndDepartment(uuid: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-course-and-department/' + uuid);
  }
  deleteClass(uuid: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/delete-class-by-id/' + uuid);
  }
  
  deleteAccountRole(username: String): Observable<any> {
    return this.http.delete(this.urlUAA + '/account?username=' + username);
  }
}

