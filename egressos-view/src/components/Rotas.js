import React from 'react'
import { Route, Routes, HashRouter } from 'react-router-dom'
import Home from '../views/Home'
import Depoimentos from '../views/Depoimentos'
import Informacoes from '../views/Informacoes'
import Cadastro from '../views/usuarios/Cadastro'
import Login from '../views/usuarios/Login'

function Rotas() {
    return (
        <HashRouter>
            <Routes>
                <Route path="/Home" element={<Home />} />
                <Route path="/Depoimentos" element={<Depoimentos />} />
                <Route path="/Informacoes" element={<Informacoes />} />
                <Route path="/Cadastro" element={<Cadastro />} />
                <Route path="/Login" element={<Login />} />
                <Route path="/" element={<Home />} />
            </Routes>
        </HashRouter>
    )
}
export default Rotas