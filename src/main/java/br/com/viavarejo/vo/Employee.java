package br.com.viavarejo.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author 7700364525
 *
 */
public class Employee implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -6012050490936966771L;
  private final static String DATE_FORMAT = "yyyy/MM/dd";
  private final static Locale localeDefault = new Locale("en", "US", "WIN");

  @SerializedName("id_person")
  private Long idPerson;

  @SerializedName("name")
  private String name;

  @SerializedName("email")
  private String email;

  @SerializedName("cpf")
  private String cpf;

  @SerializedName("internal_number")
  private String internalNumber;

  @SerializedName("cell_phone")
  private String celphone;

  // @SerializedName("groups")
  // private List<Groups> listaGroups = new ArrayList<Groups>();
  //
  // @SerializedName("leaders")
  // private List<Leaders> listaLeaders = new ArrayList<Leaders>();

  // @SerializedName("groups")
  // private String Groups;

  @SerializedName("groups")
  private Groups[] groups;

  @SerializedName("leaders")
  private Leaders[] leaders;

  @SerializedName("language")
  private String language;

  @SerializedName("blocked")
  private String blocked;

  @SerializedName("sex")
  private String sex;

  @SerializedName("birthday")
  private Date birthday;

  @SerializedName("hiring_date")
  private Date hiringDate;

  @SerializedName("resignation_date")
  private Date resignationdate;

  @SerializedName("position")
  private String position;

  @SerializedName("scholarity")
  private String scholarity;

  @SerializedName("unit_business")
  private String unitBusiness;

  @SerializedName("unit_geography")
  private String unitGeography;

  @SerializedName("last_login_at")
  private String lastLoginAt;

  @SerializedName("accessed_survey_at")
  private String accessedSurveyAt;

  @SerializedName("hiring_time_years")
  private String hiringTimeYears;

  @SerializedName("hiring_time_months")
  private String hiringTimeMonths;

  @SerializedName("age_years")
  private String ageYears;

  @SerializedName("age_months")
  private String ageMonths;


  /**
   *
   * "cpf": "248.363.998-40", "name": "TESTE DE INCLUSAO POR API", "email":
   * "teste@viavarejo.com.br", "birthday": "1974-08-18", "language": "pt-BR", "internal_number":
   * "2112345678", "id_person": 217018, "photo": null, "cell_phone": null, "leaders": null,
   * "blocked": 0, "last_login_at": null, "accessed_survey_at": null, "hiring_time_years": null,
   * "hiring_time_months": null, "age_years": 46, "age_months": 555
   *
   *
   */


  // /**
  // * @return the listaLeaders
  // */
  // public List<Leaders> getListaLeaders() {
  // return this.listaLeaders;
  // }
  //
  // /**
  // * @param listaLeaders the listaLeaders to set
  // */
  // public void setListaLeaders(final List<Leaders> listaLeaders) {
  // this.listaLeaders = listaLeaders;
  // }
  //
  // /**
  // * @return the listaGroups
  // */
  // public List<Groups> getListaGroups() {
  // return this.listaGroups;
  // }
  //
  // /**
  // * @param listaGroups the listaGroups to set
  // */
  // public void setListaGroups(final List<Groups> listaGroups) {
  // this.listaGroups = listaGroups;
  // }

  /**
   * @return the idPerson
   */
  public Long getIdPerson() {
    return this.idPerson;
  }

  public String getGroups() {
    return this.groups == null ? null : this.groups[0].toString();
  }

  public void setGroups(Groups[] groups) {
    this.groups = groups;
  }

  public String getGroupsTela() {
    return this.groups == null ? null : this.groups[0].toString();
  }

  public void setGroupsTela(String groups) {

    if ((groups != null) && !groups.isEmpty()) {

      String[] group = groups.split(",");

      Groups grupo = new Groups();

      for(int i = 0; i < group.length; i++){
        grupo.setName(grupo.getName() + group[i] + ",");
      }

//      if ((group != null) && (group.length > 0)) {
//        grupo.setIdGroup(group[0]);
//      }
//
//      if ((group != null) && (group.length > 1)) {
//        grupo.setName(group[1]);
//      }
//
//      if ((group != null) && (group.length > 2)) {
//        grupo.setName(grupo.getName() + group[2]);
//      }

      // grupo.setIdGroup("1");
      // grupo.setName("name");
      // grupo.setAncestors("");

      Groups[] arrGrup = {grupo};
      // em.setGroups(arr);

      this.groups = arrGrup;
    }
  }

  public String getLeaders() {
    return this.leaders == null ? null : this.leaders[0].toString();
  }

  public void setLeaders(Leaders[] leaders) {
    this.leaders = leaders;
  }

  public String getLeadersTela() {
    return this.leaders == null ? null : this.leaders[0].toString();
  }

  public void setLeadersTela(String leaders) {

    if ((leaders != null) && !leaders.isEmpty()) {

      String[] lead = leaders.split(",");

      Leaders leader = new Leaders();

      if ((lead != null) && (lead.length > 0)) {
        leader.setName(lead[0]);
      }
      if ((lead != null) && (lead.length > 1)) {
        leader.setCpf(lead[1]);
      }
      if ((lead != null) && (lead.length > 2)) {
        leader.setName(lead[2]);
      }

      Leaders[] arrLead = {leader};

      this.leaders = arrLead;
    }
  }

  /**
   * @param idPerson the idPerson to set
   */
  public void setIdPerson(final Long idPerson) {
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

  /**
   * @return the celphone
   */
  public String getCelphone() {
    return this.celphone;
  }

  /**
   * @param celphone the celphone to set
   */
  public void setCelphone(final String celphone) {
    this.celphone = celphone;
  }

  // /**
  // * @return the groups
  // */
  // public String getGroups() {
  // return this.groups;
  // }
  //
  // /**
  // * @param groups the groups to set
  // */
  // public void setGroups(final String groups) {
  // this.groups = groups;
  // }
  //
  // /**
  // * @return the leaders
  // */
  // public String getLeaders() {
  // return this.leaders;
  // }
  //
  // /**
  // * @param leaders the leaders to set
  // */
  // public void setLeaders(final String leaders) {
  // this.leaders = leaders;
  // }

  /**
   * @return the language
   */
  public String getLanguage() {
    return this.language;
  }

  /**
   * @param language the language to set
   */
  public void setLanguage(final String language) {
    this.language = language;
  }

  /**
   * @return the blocked
   */
  public String getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked the blocked to set
   */
  public void setBlocked(final String blocked) {
    this.blocked = blocked;
  }

  /**
   * @return the sex
   */
  public String getSex() {
    return this.sex;
  }

  /**
   * @param sex the sex to set
   */
  public void setSex(final String sex) {
    this.sex = sex;
  }

  /**
   * @return the birthday
   */
  public String getBirthday() {

    String data = null;
    if(this.birthday != null){
      data = format(this.birthday, DATE_FORMAT);
    }
    return data;
  }

  public Date getBirthdayTela() {
    return this.birthday;
  }

  public void setBirthdayTela(final Date birthday) {
    this.birthday = birthday;
  }

  public static String format(Date dtRef, String pattern) {
    SimpleDateFormat sdt = new SimpleDateFormat(pattern, localeDefault);
    return sdt.format(dtRef);
  }

  /**
   * @param birthday the birthday to set
   */
  public void setBirthday(final Date birthday) {
    this.birthday = birthday;
  }

  /**
   * @return the hiringDate
   */
  public String getHiringDate() {

    String data = null;
    if(this.hiringDate != null){
      data = format(this.hiringDate, DATE_FORMAT);
    }

    return data;
  }

  /**
   * @param hiringDate the hiringDate to set
   */
  public void setHiringDate(final Date hiringDate) {
    this.hiringDate = hiringDate;
  }

  public void setHiringDateTela(final Date hiringDate) {
    this.hiringDate = hiringDate;
  }

  public Date getHiringDateTela() {
    return this.hiringDate;
  }

  /**
   * @return the resignationdate
   */
  public String getResignationdate() {
    String data = null;

    if(this.resignationdate != null){
      data = format(this.resignationdate, DATE_FORMAT);
    }

    return data;
  }

  /**
   * @param resignationdate the resignationdate to set
   */
  public void setResignationdate(final Date resignationdate) {
    this.resignationdate = resignationdate;
  }

  /**
   * @return the resignationdate
   */
  public Date getResignationdateTela() {
    return this.resignationdate;
  }

  /**
   * @param resignationdate the resignationdate to set
   */
  public void setResignationdateTela(final Date resignationdate) {
    this.resignationdate = resignationdate;
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
   * @return the scholarity
   */
  public String getScholarity() {
    return this.scholarity;
  }

  /**
   * @param scholarity the scholarity to set
   */
  public void setScholarity(final String scholarity) {
    this.scholarity = scholarity;
  }

  /**
   * @return the unitBusiness
   */
  public String getUnitBusiness() {
    return this.unitBusiness;
  }

  /**
   * @param unitBusiness the unitBusiness to set
   */
  public void setUnitBusiness(final String unitBusiness) {
    this.unitBusiness = unitBusiness;
  }

  /**
   * @return the unitGeography
   */
  public String getUnitGeography() {
    return this.unitGeography;
  }

  /**
   * @param unitGeography the unitGeography to set
   */
  public void setUnitGeography(final String unitGeography) {
    this.unitGeography = unitGeography;
  }

  public String getLastLoginAt() {
    return this.lastLoginAt;
  }

  public void setLastLoginAt(String lastLoginAt) {
    this.lastLoginAt = lastLoginAt;
  }

  public String getAccessedSurveyAt() {
    return this.accessedSurveyAt;
  }

  public void setAccessedSurveyAt(String accessedSurveyAt) {
    this.accessedSurveyAt = accessedSurveyAt;
  }

  public String getHiringTimeYears() {
    return this.hiringTimeYears;
  }

  public void setHiringTimeYears(String hiringTimeYears) {
    this.hiringTimeYears = hiringTimeYears;
  }

  public String getHiringTimeMonths() {
    return this.hiringTimeMonths;
  }

  public void setHiringTimeMonths(String hiringTimeMonths) {
    this.hiringTimeMonths = hiringTimeMonths;
  }

  public String getAgeYears() {
    return this.ageYears;
  }

  public void setAgeYears(String ageYears) {
    this.ageYears = ageYears;
  }

  public String getAgeMonths() {
    return this.ageMonths;
  }

  public void setAgeMonths(String ageMonths) {
    this.ageMonths = ageMonths;
  }

@Override
public String toString() {
	return "Employee [idPerson=" + idPerson + ", name=" + name + ", email=" + email + ", cpf=" + cpf
			+ ", internalNumber=" + internalNumber + ", celphone=" + celphone + ", groups=" + Arrays.toString(groups)
			+ ", leaders=" + Arrays.toString(leaders) + ", language=" + language + ", blocked=" + blocked + ", sex="
			+ sex + ", birthday=" + getBirthday() + ", hiringDate=" + getHiringDate() + ", resignationdate=" + getResignationdate()
			+ ", position=" + position + ", scholarity=" + scholarity + ", unitBusiness=" + unitBusiness
			+ ", unitGeography=" + unitGeography + ", lastLoginAt=" + lastLoginAt + ", accessedSurveyAt="
			+ accessedSurveyAt + ", hiringTimeYears=" + hiringTimeYears + ", hiringTimeMonths=" + hiringTimeMonths
			+ ", ageYears=" + ageYears + ", ageMonths=" + ageMonths + "]";
}



}
