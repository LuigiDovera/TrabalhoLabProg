import ApiService from './ApiService';
class DepoimentoService extends ApiService {
    constructor () {
        super('api/depoimentos');
    }

    obterDepoimentos() {
        return this.get('/buscar');
    }

    obterDepoimentosMaisRecentes() {
        return this.get('/buscar_ordenado/1');
    }

    obterDepoimentosMaisAntigos() {
        return this.get('/buscar_ordenado/0');
    }
}
export default DepoimentoService;