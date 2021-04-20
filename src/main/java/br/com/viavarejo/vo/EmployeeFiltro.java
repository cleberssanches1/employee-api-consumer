package br.com.viavarejo.vo;

/**
 *
 * @author 7700364525
 *
 */
public class EmployeeFiltro {

    private String email;
    private String cpf;
    private String internalNumber;

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return this.cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the internalNumber
     */
    public String getInternalNumber() {
        return this.internalNumber;
    }

    /**
     * @param internalNumber the internalNumber to set
     */
    public void setInternalNumber(final String internalNumber) {
        this.internalNumber = internalNumber;
    }
}
