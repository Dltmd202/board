import axios from "axios";


const createRequest = () => {
    const instance = axios.create({
        baseURL: 'http://localhost:8080',
        timeout: 3000
    });
    return instance;
}

export const request = createRequest();