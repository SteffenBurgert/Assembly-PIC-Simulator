import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatTabsModule} from '@angular/material/tabs';


const ELEMENT_DATA: any[] = [
  {debugger: '', line: '', opcode: '', assembly_code: '00001           ;testReadFileLST'},
  {debugger: '', line: '', opcode: '', assembly_code: '00002  start'},
  {debugger: '', line: '', opcode: '', assembly_code: '00003           movlw 11h           ;W = 11h'},
  {debugger: '', line: '0000', opcode: '3930', assembly_code: '00004           andlw 30h           ;W = 10h, C=x, DC=x, Z=0'},
  {debugger: '', line: '0001', opcode: '3011', assembly_code: '00005'},
  {debugger: '', line: '0002', opcode: '380D', assembly_code: '00006           iorlw 0Dh           ;W = 1Dh, C=x, DC=x, Z=0'},
  {debugger: '', line: '0003', opcode: '3C3D', assembly_code: '00007           sublw 3Dh           ;W = 20h, C=1, DC=1, Z=0'},
  {debugger: '', line: '0004', opcode: '3A20', assembly_code: '00008           xorlw 20h           ;W = 00h, C=1, DC=1, Z=1'},
  {debugger: '', line: '0005', opcode: '3E25', assembly_code: '00009           addlw 25h           ;W = 25h, C=0, DC=0, Z=0'},
  {debugger: '', line: '', opcode: '', assembly_code: '00010'},
  {debugger: '', line: '', opcode: '', assembly_code: '00011'},
  {debugger: '', line: '', opcode: '', assembly_code: '00012  ende'},
  {debugger: '', line: '0006', opcode: '', assembly_code: '00013           goto ende'},
  {debugger: '', line: '', opcode: '', assembly_code: '00014'},
];

@Component({
  selector: 'asm-pic-simulator',
  standalone: true,
  imports: [MatButtonModule, MatTableModule, MatFormFieldModule, MatInputModule, MatTabsModule],
  templateUrl: './simulator.component.html',
  styleUrl: './simulator.component.scss',
})
export class SimulatorComponent {
  displayedColumns: string[] = ['debugger', 'line', 'opcode', 'assembly_code'];
  dataSource = ELEMENT_DATA;
}
