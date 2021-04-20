package br.com.viavarejo.vo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author 7700364525
 *
 */
public class Leaders implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8925242125730638339L;

    @SerializedName("id_person")
    private Integer idPerson;

    @SerializedName("name")
    private String name = "";

    @SerializedName("cpf")
    private String cpf = "";

    @SerializedName("position")
    private String position = "";

    @SerializedName("email")
    private String email = "";

    @SerializedName("internal_number")
    private String internalNumber = "";

    public String getCpf() {
      return this.cpf;
    }

    public void setCpf(String cpf) {
      this.cpf = cpf;
    }

    /**
     * @return the idPerson
     */
    public Integer getIdPerson() {
        return this.idPerson;
    }

    /**
     * @param idPerson the idPerson to set
     */
    public void setIdPerson(final Integer idPerson) {
        this.idPerson = idPerson;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(final String position) {
        this.position = position;
    }

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

    @Override
    public String toString() {
      return   this.name + ";" + this.cpf + ";" + this.internalNumber;
    }

}
