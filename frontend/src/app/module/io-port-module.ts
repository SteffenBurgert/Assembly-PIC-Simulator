export class IOPort {
  constructor(
    public portA: number[] = new Array(5).fill(0),
    public portB: number[] = new Array(8).fill(0),
    public trisA: number[] = new Array(5).fill(0),
    public trisB: number[] = new Array(8).fill(0),
  ) {}
}
