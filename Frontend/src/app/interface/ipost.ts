import { IComment } from './icomment';
import { IUser } from './iuser';

export interface IPost {
    postId: number;
    body: string;
    owner: IUser;
    likes: IUser[];
    imageSrc: string;
    comments: IComment[];
    youtubeLink: string;
    created: number;
}

