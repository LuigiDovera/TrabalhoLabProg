import ApiService from './ApiService'
class CursoService extends ApiService {
    constructor () {
        super('api/cursos')
    }
    obterCursos() {
        return this.get('/buscar')
    }
    quantidaDeEgressosPorCurso(id) {
        return this.get(`/listar_quantidade_egressos_por_curso/${id}`)
    }
}
export default CursoService