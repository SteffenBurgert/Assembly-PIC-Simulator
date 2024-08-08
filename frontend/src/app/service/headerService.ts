import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class HeaderService {
  constructor() {}

  public getHeader(): HttpHeaders {
    return new HttpHeaders();
  }
}
