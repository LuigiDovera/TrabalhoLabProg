import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080',
});

class ApiService {
    constructor(apiUrl, apiToken = null) {
        this.apiUrl = apiUrl
        instance.defaults.headers.common['Authorization'] = apiToken;
        this.loginUrl = 'login'
    }

    post(url, objeto) {
        return instance.post(`${this.apiUrl}${url}`, objeto)
    }
    put(url, objeto) {
        return instance.put(`${this.apiUrl}${url}`, objeto)
    }
    delete(url) {
        return instance.delete(`${this.apiUrl}${url}`)
    }
    get(url) {
        return instance.get(`${this.apiUrl}${url}`)
    }
    login(objeto) {
        return instance.post(`${this.loginUrl}`, objeto)
    }
}

export default ApiService