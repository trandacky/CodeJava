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
  private urlUAAResource= SERVER_API_URL+'services/uaa/api/resource';
  constructor(private http: HttpClient) { }
  getInfoAccount(): Observable<any> {
    return this.http.get(this.urlUAAResource + `/info`);
  }
  getAllTypeReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-type-report?page=${page}`);
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
  getClassReportKhoa(formData:FormData): Observable<any> {
    return this.http.post(this.urlService + '/get-all-class-by-year-and-semester',formData);
  }
  getReportByClassKhoa(formData:FormData): Observable<any> {
    return this.http.post(this.urlService + '/get-all-report-by-class-year-and-semester',formData);
  }
  createReportAndDetailbyClass(data: FormData): Observable<any> {
    return this.http.post(this.urlService+
      '/create-report-and-detail-report-by-type-report-and-classuuid', data);
  }
  createReportAndDetailAllClass(data: FormData): Observable<any> {
    return this.http.post(this.urlService+
      '/create-report-and-detail-report-by-type-report-all-account', data);
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
  getAllReport(formData: FormData): Observable<any> {
    return this.http.post(this.urlService + `/get-all-report-by-class-year-and-semester`,formData);
  }
  getDetailReport(id: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-detail-report-by-report-id?id=${id}`);
  }
  updateAllAccepted3(formData: FormData): Observable<any> {
    return this.http.put(this.urlService + '/update-all-accepted3',formData);
  }
  updateAccepted3(id: Number, accepted3:Boolean): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('accepted3', accepted3.toString());
    return this.http.put(this.urlService + '/update-accepted3', formData);
  }
  updateTotalScore3(id: Number): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    return this.http.put(this.urlService + '/update-total-score3', formData);
  }
  updateDetailScore3(id: Number, score3:Number): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('score3', score3.toString());
    return this.http.put(this.urlService + '/update-detail-score3', formData);
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

