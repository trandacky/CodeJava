import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class KhoaService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/admin';
  private urlUaa = SERVER_API_URL+'services/uaa/api/admin';

  constructor(private http: HttpClient) { }
  getAllTypeReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-type-report?page=${page}`);
    // return this.http.get(`${this.url}` + `/get-all-type-report${paging? paging: ''}`);
  }
  createTypeReport(typeName: string): Observable<any> {
    const typeReportParam = new FormData();
    typeReportParam.append('typename',typeName);
    return this.http.post(this.urlService+'/create-type-report?typeName=', typeReportParam);
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
  deleteEvaluation(id: Number):Observable<any>
  {
    return this.http.delete(`${this.urlService}/delete-evaluation-report/`+id);
  }
 
  createNewEvaluation(formEvaluationCriteria: FormData):Observable<any>
  {
    return this.http.post<any>(`${this.urlService}/create-evaluation-criteria`, formEvaluationCriteria);
  }
  // updateEvaluation(formEvaluationCriteria: FormData):Observable<any>
  // {
  //   return this.http.put<any>(`${this.url}/update-evaluation-criteria`, formEvaluationCriteria);
  // }
  getClass(page: Number): Observable<any> {
    return this.http.get(this.urlUaa + `/get-paging-class?page=${page}`);
  }

}

