import React from 'react'
import { Route, Routes, HashRouter } from 'react-router-dom'
import Home from '../views/Home'
import Depoimentos from '../views/Depoimentos'
import DepoimentosToggle from '../views/DepoimentosToggle'
import Informacoes from '../views/Informacoes'
import Cadastro from '../views/usuarios/Cadastro'
import Login from '../views/usuarios/Login'
import Egresso from '../views/Egresso'

function Rotas() {
    return (
        <HashRouter>
            <Routes>
                <Route path="/Home" element={<Home />} />
                <Route path="/Depoimentos" element={<Depoimentos />} />
                <Route path="/Depoimentos/MaisRecente" element={<Depoimentos depoimentoRecente={true}/>} />
                <Route path="/Depoimentos/MaisAntigo" element={<DepoimentosToggle depoimentoRecente={false}/>} />
                <Route path="/Informacoes" element={<Informacoes />} />
                <Route path="/Cadastro" element={<Cadastro />} />
                <Route path="/Login" element={<Login />} />
                <Route path="/Egresso/:id" element={<Egresso />} />
                <Route path="/" element={<Home />} />
            </Routes>
        </HashRouter>
    )
}
export default Rotas