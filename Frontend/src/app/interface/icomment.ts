import { IPost } from './ipost';
import { IUser } from './iuser';

export interface IComment {
    post: IPost;
    owner: IUser;
    body: string;
    created: number;
}
