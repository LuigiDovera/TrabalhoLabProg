import ApiService from './ApiService'
class UsuarioService extends ApiService {
    constructor () {
        super('api/egressos')
    }
    logar(email, senha) {
        return this.login({ "email": email, "senha": senha })
    }
    obterEgressos() {
        return this.get('/buscar')
    }
    buscarEgresso(email) {
        return this.get(`/buscar/${email}`)
    }
}
export default UsuarioService