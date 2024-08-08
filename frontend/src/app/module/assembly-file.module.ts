import { FileType } from './file-type.enum';
import { LstFile } from './file-upload.module';
import { IOPort as IOPins } from './io-port-module';
import { Ram } from './ram.module';

export class AssemblyFile {
  constructor(
    public fileName: string | undefined = undefined,
    public fileType: FileType | undefined = undefined,
    public ram: Ram = new Ram(),
    public ioPins: IOPins = new IOPins(),
    public lstFile: LstFile[] | undefined = undefined,
  ) {}
}
