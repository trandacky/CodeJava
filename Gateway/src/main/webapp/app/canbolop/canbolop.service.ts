import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class CanBoLopService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/canbolop';
  private urlUAA = SERVER_API_URL+'services/uaa/api/canbolop';
  private urlUAAResource= SERVER_API_URL+'services/uaa/api/resource';
  constructor(private http: HttpClient) { }
  
  getAllReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-report-by-classid?page=${page}`);
  } 
  getDetailReport(id: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-detail-report-by-report-id?id=${id}`);
  }  
  getDetailAccount(username:String): Observable<any> {
    return this.http.get(this.urlUAA + `/get-account-info-class?username=${username}`);
  }  
  updateDetailReportScore2(id:Number,score:Number): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('score2', score.toString());
    return this.http.put(this.urlService + '/update-score2',formData);
  }  
  updateTotalScore2(id:Number): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    return this.http.put(this.urlService + `/update-total-score2`,formData);
  }  
}

