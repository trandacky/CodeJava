import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../app.constants';

@Injectable({ providedIn: 'root' })
export class AdminService {
  constructor(private http: HttpClient) { }

  createRole(role: String): Observable<{}> {
    const roleForm = new FormData();
    roleForm.append('authorities', role.toString());
    return this.http.post(SERVER_API_URL + 'services/uaa/api/admin/create-authority', roleForm);
  }
  getAllRole(): Observable<{}> {
    return this.http.get(SERVER_API_URL + 'services/uaa/api/admin/get-all-authority');
  }
  deleteRole(authorities: String): Observable<{}> {
    return this.http.delete(SERVER_API_URL + 'services/uaa/api/admin/delete-authority/'+authorities);
  }
}
