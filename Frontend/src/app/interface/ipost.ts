import { IComment } from './icomment';
import { IUser } from './iuser';

export interface IPost {
    body: string;
    owner: IUser;
    likes: IUser[];
    imageSrc: string;
    comments: IComment[];
}

