import ApiService from './ApiService'
class CargoService extends ApiService {
    constructor () {
        super('api/cargos')
    }
    obterCargos() {
        return this.get('/buscar')
    }
    quantidaDeEgressosPorCargo(cargo) {
        return this.get(`/quantidade_egressos_por_cargo/${cargo.id}`)
    }
}
export default CargoService