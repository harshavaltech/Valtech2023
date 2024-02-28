import axios from 'axios';
import { getToken, removeToken } from './AuthService';
import { updateLoginStatus } from '../../../Book-My-Seat/src/components/auth'

const instance = axios.create({
  baseURL: 'http://localhost:5000/bookmyseat',
});

instance.interceptors.request.use(
  (config) => {
    const accessToken = getToken();
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      updateLoginStatus(false);
      removeToken();
      sessionStorage.removeItem('userData');
      

      window.location.replace('/bookmyseat/login');
      return Promise.reject(error);
    }

    return Promise.reject(error);
  }
);

export default instance;
