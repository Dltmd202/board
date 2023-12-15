import axios from "axios";

const createRequest = () => {
  const instance = axios.create({
    baseURL: process.env.REACT_APP_API_END_POINT,
    timeout: 3000
  });
  return instance;
}

const createAuthRequest = () => {
  const instance = createRequest();

  instance.interceptors.request.use(
    (config) => {
      const sessionId = localStorage.getItem("TOKEN");
      config.headers["Authorization"] = `Bearer ${sessionId}`;
      return config;
    },
    (error) => Promise.reject(error.response),
  );

  instance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      if(error.response.status === 401) {
        await localStorage.removeItem("TOKEN");
        await localStorage.removeItem("NAME");
      }
      return Promise.reject(error.response);
    });

  return instance;
};


export const request = createRequest();

export const authRequest = createAuthRequest();