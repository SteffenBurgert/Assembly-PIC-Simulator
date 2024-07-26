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
import { FileUpload } from 'src/app/module/file-upload.module';

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
export class SimulatorComponent {
  isIOopen: boolean = false;
  gprValues: number[] = [];
  fileName: string = '';
  fileUploadASM: FileUpload[] = [];

  constructor(private uploadFileService: UploadFileService) {}

  public toggleIsIOopen(): void {
    this.isIOopen = !this.isIOopen;
  }

  public onFileSelected(event: any): void {
    const file: File = event.target.files[0];

    if (file) {
      const formData = new FormData();

      formData.append('file', file);

      this.uploadFileService
        .uploadFile(formData)
        .pipe()
        .subscribe({
          next: (fileUpload: FileUpload[]) => {
            this.fileUploadASM = fileUpload;
            this.fileName = file.name;
          },
          error: () => {
            console.log('Error');
          },
        });
    }
  }
}
