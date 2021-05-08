import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { SERVER_API_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class SinhVienService {
 
  private urlService = SERVER_API_URL+'services/microserviceevaluationreport/api/sinhvien';
  private urlUAA = SERVER_API_URL+'services/uaa/api/sinhvien';
  private urlUAAResource= SERVER_API_URL+'services/uaa/api/resource';
  constructor(private http: HttpClient) { }
  
  getAllReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-report-by-username?page=${page}`);
  }
  getInfoAccount(): Observable<any> {
    return this.http.get(this.urlUAAResource + `/info`);
  }
  getDetailReport(id: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-detail-report?id=${id}`);
  }
  updateDetailReportScore1(id: Number, score1: number, note: String): Observable<any> {
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('score1', score1.toString());
    formData.append('note', note.toString());
    return this.http.put(this.urlService + '/update-detail-report-scrore1-and-note', formData);
  }
  updateTotalScore(id: Number): Observable<any> {
    const formData = new FormData();
    formData.append('idreport', id.toString());
    return this.http.put(this.urlService + '/update-report-total-score1', formData);
  }
  
}

