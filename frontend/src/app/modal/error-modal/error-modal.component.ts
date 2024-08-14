import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'asm-pic-error-modal',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './error-modal.component.html',
  styleUrl: './error-modal.component.scss',
})
export class ErrorModalComponent {
  @Input() message: string | undefined;
  @Output() closed: EventEmitter<boolean> = new EventEmitter<boolean>();

  public closeErrorModal(): void {
    this.closed.emit(true);
  }
}
