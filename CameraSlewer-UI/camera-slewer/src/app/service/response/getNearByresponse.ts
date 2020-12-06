import { ErrorVo } from './ErrorVo';
import { Location } from '../../models/location';
import { Camera } from '../../models/camera';

export class GetNearByResponse {
    locations: Location[];
    status: String;
    error: ErrorVo;
    camera: Camera;

}