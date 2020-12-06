import { ErrorVo } from './ErrorVo';
import { Location } from '../../models/location';

export class LocationResponse {
    locations: Location[];
    status: String;
    error: ErrorVo;
}