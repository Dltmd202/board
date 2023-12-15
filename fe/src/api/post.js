import {authRequest} from "./index";

export const postApi =  {
    getPosts: (page) => { return authRequest.get(`/api/v1/posts?size=10&page=${page - 1}`) },
    getPost: (postId) => { return authRequest.get(`/api/v1/posts/${postId}`) },
    createPost: (post) => { return authRequest.post(`/api/v1/posts`, post) },
    deletePost: (postId) => { return authRequest.delete(`/api/v1/posts/${postId}`) },
    editPost: (postId, post) => { return authRequest.put(`/api/v1/posts/${postId}`, post) },
}
