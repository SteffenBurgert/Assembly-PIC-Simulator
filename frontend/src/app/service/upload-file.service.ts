import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileUpload } from '../module/file-upload.module';
import { HeaderService } from './headerService';
import { environment } from '../envirnonment/environment';

@Injectable({ providedIn: 'root' })
export class UploadFileService {
  private apiServerUrl = environment.baseUrl + 'file/';

  constructor(
    private readonly http: HttpClient,
    private headerService: HeaderService,
  ) {}

  public uploadFile(formData: FormData): Observable<FileUpload[]> {
    return this.http.post<FileUpload[]>(this.apiServerUrl + 'upload', formData, {
      headers: this.headerService.getHeader(),
    });
  }
}
