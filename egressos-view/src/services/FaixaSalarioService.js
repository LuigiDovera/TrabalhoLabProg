import ApiService from './ApiService'
class FaixaSalarioService extends ApiService {
    constructor () {
        super('api/faixa_salario')
    }
    obterFaixasSalariais() {
        return this.get('/buscar')
    }
    quantidaDeEgressosPorFaixaSalarial(id) {
        return this.get(`/quantidade_egresso_por_faixa_salario/${id}`)
    }
}
export default FaixaSalarioService