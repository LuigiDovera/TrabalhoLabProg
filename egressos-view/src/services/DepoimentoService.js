import ApiService from './ApiService'
class DepoimentoService extends ApiService {
    constructor () {
        super('api/depoimentos')
    }
    obterDepoimentos() {
        return this.get('/buscar')
    }
}
export default DepoimentoService