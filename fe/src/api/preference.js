import {authRequest} from "./index";

export const preferenceApi = {
  like: (postId) => { return authRequest.post(`/api/v1/posts/${postId}/preference?type=LIKE`) },
  unlike: (postId) => { return authRequest.post(`/api/v1/posts/${postId}/preference?type=UNLIKE`) }
}