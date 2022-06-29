import {Message} from './message'
import {Object} from './object'


export class Report{
    object = new Object;
    messages = Array<Message> ();
    dateFrom = '';
    dateTo = '';

}