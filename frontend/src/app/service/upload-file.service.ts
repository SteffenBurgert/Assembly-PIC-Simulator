import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HeaderService } from './headerService';
import { environment } from '../envirnonment/environment';
import { AssemblyFile } from '../module/assembly-file.module';

@Injectable({ providedIn: 'root' })
export class UploadFileService {
  private apiServerUrl = environment.baseUrl + 'file/';

  constructor(
    private readonly http: HttpClient,
    private headerService: HeaderService,
  ) {}

  public uploadFile(formData: FormData): Observable<AssemblyFile> {
    return this.http.post<AssemblyFile>(this.apiServerUrl + 'upload', formData, {
      headers: this.headerService.getHeader(),
    });
  }
}
