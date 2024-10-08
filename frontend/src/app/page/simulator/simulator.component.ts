import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { UploadFileService } from 'src/app/service/upload-file.service';
import { AssemblyFile } from 'src/app/module/assembly-file.module';
import { takeUntil } from 'rxjs';
import { Unsub } from 'src/app/utils/unsub.class';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'asm-pic-simulator',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatTabsModule,
    MatCardModule,
    MatIconModule,
  ],
  templateUrl: './simulator.component.html',
  styleUrl: './simulator.component.scss',
})
export class SimulatorComponent extends Unsub {
  isIOopen: boolean = false;
  isRun: boolean = false;
  asmFile: AssemblyFile = new AssemblyFile();

  constructor(private uploadFileService: UploadFileService) {
    super();
  }

  public toggleIsIOopen(): void {
    this.isIOopen = !this.isIOopen;
  }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  public onFileSelected(event: any): void {
    const file: File = event.target.files[0];

    if (file) {
      const formData: FormData = new FormData();

      formData.append('file', file);

      this.uploadFileService
        .uploadFile(formData)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe({
          next: (assemblyFile: AssemblyFile) => {
            this.asmFile = assemblyFile;
          },
          error: (error: HttpErrorResponse) => {
            alert(error);
          },
        });
    }
  }

  public setResetDebug(index: number): void {
    if (this.asmFile.lstFile !== undefined && this.asmFile.lstFile[index].opcode !== '') {
      this.asmFile.lstFile[index].isDebug = !this.asmFile.lstFile[index].isDebug;
    }
  }

  public toggleIsRun(): void {
    this.isRun = !this.isRun;
  }

  public resetIsRun(): void {
    this.isRun = false;
  }
}
