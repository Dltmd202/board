import axios from "axios";

const createRequest = () => {
  const instance = axios.create({
    baseURL: process.env.REACT_APP_API_END_POINT,
    timeout: 3000
  });
  return instance;
}

const setAuthInterceptors = () => {
  const instance = createRequest();
  const sessionId = localStorage.getItem("TOKEN");

  instance.interceptors.request.use(
    (config) => {
      config.headers["Authorization"] = `Bearer ${sessionId}`;
      return config;
    },
    (error) => Promise.reject(error.response),
  );

  return instance;
};


export const request = createRequest();

export const authRequest = setAuthInterceptors();