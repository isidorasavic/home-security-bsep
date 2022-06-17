import {User} from './user'


export class Object{
    id = 0;
    name = '';
    type = '';
    owner = new User();
    tenants = Array<User>()

    // constructor(id: number, name: string, type: string, owner: User, tenants: Array<User>) {
    //     this.id = id;
    //     this.name = name;
    //     this.type = type;
    //     this.owner = owner;
    //     this.tenants = tenants;
    // }

}