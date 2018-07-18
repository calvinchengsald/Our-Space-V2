import { IPost } from './iPost';
import { IUser } from './iuser';

export interface IComment {
    post: IPost;
    owner: IUser;
    body: string;
}
