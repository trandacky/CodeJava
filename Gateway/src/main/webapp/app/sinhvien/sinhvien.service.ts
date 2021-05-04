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

  constructor(private http: HttpClient) { }
  
  getAllReport(page: Number): Observable<any> {
    return this.http.get(this.urlService + `/get-all-report-by-username?page=${page}`);
  }
}

