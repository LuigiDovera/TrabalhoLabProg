import ApiService from './ApiService'

class EgressoService extends ApiService {
    constructor () {
        super('api/egressos')
    }
    obterEgressos() {
        return this.get('/buscar')
    }
    obterEgressoPorId(id) {
        return this.get(`/buscar_id/${id}`)
    }
}
export default EgressoService