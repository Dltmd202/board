import {authRequest} from "./index";

export const commentApi = {
  getComments: (postId, page) => {
    return authRequest.get(`/api/v1/posts/${postId}/comments?size=5&page=${page - 1}`)
  },
  createComment: (postId, content) => {
    return authRequest.post(`/api/v1/posts/${postId}/comments`, {content: content})
  },
  deleteComment: (postId, commentId) => {
    return authRequest.delete(`/api/v1/posts/${postId}/comments/${commentId}`)
  }
}