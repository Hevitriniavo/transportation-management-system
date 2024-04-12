import { initializeAPI } from "./axios";

export async function postData<T, R>(url: string, requestData: T, headers?: { [key: string]: string }): Promise<R> {
    try {
        const api = initializeAPI(headers);
        const response = await api.post<R>(url, requestData);
        return response.data;
    } catch (err) {
        console.error("Une erreur s'est produite lors de la récupération des données :", err);
        throw err;
    }
};
