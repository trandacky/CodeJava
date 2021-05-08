import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class GiangVienService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/giangvien';
  private urlUAA = SERVER_API_URL+'services/uaa/api/giangvien';
  private urlUAAResource= SERVER_API_URL+'services/uaa/api/resource';
  constructor(private http: HttpClient) { }
  
  getAllReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-report-by-class-id?page=${page}`);
  }
  getInfoAccount(): Observable<any> {
    return this.http.get(this.urlUAAResource + `/info`);
  }
  getDetailReport(id: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-detail-report-by-report-id?id=${id}`);
  }
  updateAllAccepted2(): Observable<any> {
    return this.http.put(this.urlService + '/update-all-report-accepted2',null);
  }
  updateAccepted2(id: Number, accepted2:Boolean): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('accepted2', accepted2.toString());
    return this.http.put(this.urlService + '/update-accepted2', formData);
  }
  
}

