import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

import { environment } from './envirnonment/environment';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, MatIconModule, MatButtonModule, MatToolbarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'Assembly-PIC-Simulator';
  gitBranchName: string | undefined;
  currentYear = new Date().getFullYear();
  isNavbarLeftOpen: boolean = false;

  ngOnInit(): void {
    this.gitBranchName = environment.gitBranch;
  }

  public toggleNavbar(): void {
    this.isNavbarLeftOpen = !this.isNavbarLeftOpen;
  }
}
