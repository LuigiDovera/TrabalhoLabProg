import ApiService from './ApiService'
class UsuarioService extends ApiService {
    constructor () {
        super('api/egressos')
    }
    logar(email, senha) {
        return this.login({ "email": email, "senha": senha })
    }
    cadastrar(egresso) {
        return this.post('/salvar', egresso)
    }
    atualizar(egresso, token) {
        return this.put(`/atualizar/${egresso.id}`, egresso, token)
    }
    obterEgressos() {
        return this.get('/buscar')
    }
    buscarEgresso(email, token) {
        return this.get(`/buscar/${email}`, token)
    }
}
export default UsuarioService