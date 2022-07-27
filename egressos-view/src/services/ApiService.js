import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Access-Control-Allow-Origin': '*',
    }
});

class ApiService {
    constructor(apiUrl, apiToken = null) {
        this.apiUrl = apiUrl
        instance.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
        this.loginUrl = 'login'
        this.authHeader = 'Authorization'
    }

    post(url, objeto, token=null) {
        return instance.post(`${this.apiUrl}${url}`, objeto, {headers: {[`${this.authHeader}`] : `${token}`}})
    }
    put(url, objeto, token=null) {
        return instance.post(`${this.apiUrl}${url}`, objeto, {headers: {[`${this.authHeader}`] : `${token}`}})
    }
    delete(url, token=null) {
        return instance.delete(`${this.apiUrl}${url}`, {headers: {[`${this.authHeader}`] : `${token}`}})
    }
    get(url, token=null) {
        return instance.get(`${this.apiUrl}${url}`, {headers: {[`${this.authHeader}`] : `${token}`}})
    }
    login(objeto) {
        return instance.post(`${this.loginUrl}`, objeto)
    }
}

export default ApiService