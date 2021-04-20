package br.com.viavarejo.services;

import java.util.List;

import br.com.viavarejo.vo.Employee;
import br.com.viavarejo.vo.EmployeeFiltro;
import br.com.viavarejo.vo.Groups;

/**
 *
 * @author cleber
 *
 */
public interface EmployeeServices {
	/**
	 * get employee
	 * 
	 * @param filtro EmployeeFiltro
	 * @return List<Employee>
	 * @throws Exception Exception
	 */
	List<Employee> buscarDadosEmployees(EmployeeFiltro filtro) throws Exception;

	/**
	 * Create a employee
	 * 
	 * @param employee Employee
	 * @return String
	 * @throws Exception Exception
	 */
	String incluirEmployee(final Employee employee) throws Exception;

	/**
	 * Import employees
	 * 
	 * @param employees Employee
	 * @return String
	 * @throws Exception Exception
	 */
	String incluirListaEmployee(final List<Employee> employees) throws Exception;

	/**
	 * Delete a employee
	 * 
	 * @param employee List<Employee>
	 * @return String
	 * @throws Exception Exception
	 */
	String deleteEmployee(final Employee employee) throws Exception;

	/**
	 * Update a employee
	 * 
	 * @param employee Employee
	 * @return String
	 * @throws Exception Exception
	 */
	String updateEmployee(final Employee employee) throws Exception;

	/**
	 * Add a employee to group
	 * 
	 * @param idPerson String
	 * @param idGroup  String
	 * @return String
	 * @throws Exception Exception
	 */
	String incluirClienteGrupo(final String idPerson, final String idGroup) throws Exception;

	/**
	 * Delete a employee from group
	 * 
	 * @param idPerson String
	 * @param idGroup  String
	 * @return String
	 * @throws Exception Exception
	 */
	String deletarClienteGrupo(final String idPerson, final String idGroup) throws Exception;

	/**
	 * Delete a group
	 * 
	 * @param idGroup String
	 * @return String
	 * @throws Exception Exception
	 */
	String deletarGrupo(final String idGroup) throws Exception;

	/**
	 * Get all groups in list format
	 * 
	 * @return List<Groups>
	 * @throws Exception Exception
	 */
	List<Groups> buscarGroups() throws Exception;

	/**
	 * Get all groups in tree list format
	 * 
	 * @return List<Groups>
	 * @throws Exception Exception
	 */
	List<Groups> buscarArvoreGroups() throws Exception;

	/**
	 * Create a group
	 * 
	 * @param name      String
	 * @param parent_id String
	 * @return String
	 * @throws Exception Exception
	 */
	String incluirGrupoPost(final String name, final String parent_id) throws Exception;

	/**
	 * Find groups by name
	 * 
	 * @param name String
	 * @return Groups
	 * @throws Exception Exception
	 */
	Groups buscarDadosGroup(String name) throws Exception;

	/**
	 * Get group
	 * 
	 * @param idGroup String
	 * @return Groups
	 * @throws Exception Exception
	 */
	Groups buscarGroupsById(String idGroup) throws Exception;

	/**
	 * Update a group
	 * @param group Groups
	 * @return String
	 * @throws Exception Exception
	 */
	String updateGroups(final Groups group) throws Exception;

	/**
	 * Get all employees
	 * 
	 * @return List<Employee>
	 * @throws Exception Exception
	 */
	List<Employee> buscarEmployes() throws Exception;
}
