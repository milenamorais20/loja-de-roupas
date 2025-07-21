package sistema.gestao.vendas.loja.models;

public enum UserRole {
    FUNCIONARIO("funcionario"),
    CLIENTE("cliente");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
