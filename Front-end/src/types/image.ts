export default class Image
{
  private _path: string;
  private _height: number;
  private _width: number;

  constructor(path: string, width: number, height: number)
  {
    this._path = path;
    this._height = height;
    this._width = width;
  }
  
  public get path(): string { return this._path }
  public get height(): number { return this._height }
  public get width(): number { return this._width }
}