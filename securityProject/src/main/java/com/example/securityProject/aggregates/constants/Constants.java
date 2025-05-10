package com.example.securityProject.aggregates.constants;

public class Constants {
    public static final Boolean STATUS_ACTIVE = true;
    public static final String CLAVE_AccountNonExpired ="isAccountNonExpired";
    public static final String CLAVE_AccountNonLocked ="isAccountNonLocked";
    public static final String CLAVE_CredentialsNonExpired = "isCredentialsNonExpired";
    public static final String CLAVE_Enabled = "isEnabled";
    public static final String ACCESS = "access";
    public static final String ENDPOINTS_PERMIT = "/api/auth/**";
    public static final String ENDPOINTS_USER= "/api/test/user/**";
    public static final String ENDPOINTS_ADMIN = "/api/test/admin/**";
    public static final String ENDPOINTS_SUPERADMIN = "/api/test/superadmin/**";
    public static final String MSJ_OK="Consulta exitosa";
    public static final Integer CODE_OK=200;
    public static final String MSJ_DUPLICATED="Usuario ya existente";
    public static final Integer CODE_EXIST=400;
}
