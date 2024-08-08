export class LstFile {
  constructor(
    public isDebug: boolean = false,
    public line: string = '',
    public opcode: string = '',
    public assemblyCode: string = '',
  ) {}
}
