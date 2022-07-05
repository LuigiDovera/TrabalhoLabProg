import ApiService from './ApiService'
class CargoService extends ApiService {
    constructor () {
        super('api/cargos')
    }
    obterCargos() {
        return this.get('/buscar')
    }
    quantidaDeEgressosPorCargo(id) {
        return this.get(`/quantidade_egressos_por_cargo/${id}`)
    }
}
export default CargoService