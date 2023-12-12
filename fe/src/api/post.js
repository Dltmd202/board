import {authRequest} from "./index";

export const postApi =  {
    getPosts: (page) => { return authRequest.get(`/api/v1/posts?size=10&page=${page - 1}`) },
}