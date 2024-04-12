import axios, { AxiosInstance, AxiosRequestConfig } from "axios";

let instance: AxiosInstance | null = null;

export function initializeAPI(headers?: { [key: string]: string }): AxiosInstance {
  if (!instance) {
    const config: AxiosRequestConfig = {
      baseURL: "http://localhost:8080/api",
    };

    if (headers) {
      config.headers = headers; 
    }

    instance = axios.create(config);
  }

  return instance;
}
