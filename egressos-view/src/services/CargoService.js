import ApiService from './ApiService'
class CargoService extends ApiService {
    constructor () {
        super('api/cargos')
    }
    obterCargos() {
        return this.get('/buscar')
    }

    obterCargosPorIdEgresso(idEgresso) {
        return this.get(`/buscar_por_egresso/${idEgresso}`);
    }

    obterCargoPorIdProfEgresso(idProfEgresso){
        return this.get(`/buscar_por_profegresso/${idProfEgresso}`);
    }

    quantidaDeEgressosPorCargo(id) {
        return this.get(`/quantidade_egressos_por_cargo/${id}`)
    }
}
export default CargoService