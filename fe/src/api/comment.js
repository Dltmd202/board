import {authRequest} from "./index";

export const commentApi = {
    getComments: (postId, page) => {
      return authRequest.get(`/api/v1/posts/${postId}/comments?size=5&page=${page - 1}`)
    },
}