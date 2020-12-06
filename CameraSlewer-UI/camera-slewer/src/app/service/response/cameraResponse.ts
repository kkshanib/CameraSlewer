import { Camera } from '../../models/camera';
import { ErrorVo } from './ErrorVo';

export class CameraResponse {
    camerraList: Camera[];
    status: String;
    error: ErrorVo;
  
  }