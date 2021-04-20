package br.com.viavarejo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author 7700364525
 *
 */
public class Groups implements Serializable {

	@Override
	public String toString() {
		return this.idGroup + "," + this.name;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8014874808757249576L;
	@SerializedName("id_group")
	private String idGroup = "";
	@SerializedName("name")
	private String name = "";
	@SerializedName("ancestors")
	private String ancestors = "";
	@SerializedName("groups")
	private List<Groups> listaGroups = new ArrayList<Groups>();
	@SerializedName("parent_id")
	private String parentId;
	@SerializedName("employees")
	private String employees;
	@SerializedName("total")
	private String total;

	public List<Groups> getListaGroups() {
		return listaGroups;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public void setListaGroups(List<Groups> listaGroups) {
		this.listaGroups = listaGroups;
	}

	/**
	 * @return the idGroup
	 */
	public String getIdGroup() {
		return this.idGroup;
	}

	/**
	 * @param idGroup
	 *            the idGroup to set
	 */
	public void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public String getAncestors() {
		return this.ancestors;
	}

	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}

}
