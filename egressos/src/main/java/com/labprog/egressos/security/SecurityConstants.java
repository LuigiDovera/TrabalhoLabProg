package com.labprog.egressos.security;

public class SecurityConstants {
    public static final String SIGN_UP_URL = "/api/egressos/salvar";
    public static final String EGRESSO_POR_ID_URL = "/api/egressos/buscar_id/{id}";
    public static final String EGRESSOS_URL = "/api/egressos/buscar";
    public static final String GARGO_URL = "/api/cargos/**";
    public static final String CURSO_URL = "/api/cursos/**";
    public static final String DEPOIMENTO_URL = "/api/depoimentos/**";
    public static final String FAIXA_SALARIO_URL = "/api/faixa_salario/**";
    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L * 60 * 30;
}
