export class Ram {
  constructor(
    public wRegister: number = 0,
    public pc: number = 0,
    public pcl: number = 0,
    public pclath: number = 0,
    public carry: number = 0,
    public digitCarry: number = 0,
    public zeroFlag: number = 0,
    public bank0: number[] = new Array(128).fill(0),
    public bank1: number[] = new Array(128).fill(0),
    public tos: number[] = [],
  ) {}
}
