import {request} from './index';

export const userApi = {
    signup: (data) => { return request.post('/api/v1/users', data) },
    validateEmail: (email) => { return request.get(`/api/v1/users/email?email=${email}`) }
}