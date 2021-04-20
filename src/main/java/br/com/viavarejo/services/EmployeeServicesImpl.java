package br.com.viavarejo.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.viavarejo.vo.Employee;
import br.com.viavarejo.vo.EmployeeFiltro;
import br.com.viavarejo.vo.Groups;
import br.com.viavarejo.vo.Leaders;
//import br.com.viavarejo.vo.ResponseObject;

/**
 *
 * @author cleber
 *
 */
public class EmployeeServicesImpl implements EmployeeServices {

	private final static Locale localeDefault = new Locale("en", "US", "WIN");
	private static final String EMPLOYEE_API = "https://www.pulses.com.br/api/engage_sandbox/v1/";

	private String employee_api = "";
	private String authorization = "";
	/**
	 *
	 */
	@Autowired
	private final RestTemplate restTemplate = new RestTemplate();

	public EmployeeServicesImpl(String apiUrl, String authorization) {
		this.employee_api = apiUrl;
		this.authorization = authorization;
	}

	/**
	 * Retorna listagem de grupos
	 * https://www.pulses.com.br/api/engage_sandbox/v1/groups/
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Employee> buscarEmployes() throws Exception {

		String url = employee_api + "/employees/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		List<Employee> employees = new ArrayList<Employee>();

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);

			String jsonText = response.getBody();

			if (response.getBody().contains("No employee found")) {
				throw new Exception("No employee found");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			String mat = jsonResponse.getJSONArray("values").toString();

			Gson gson = new Gson();

			java.lang.reflect.Type collectionType = new TypeToken<Collection<Employee>>() {
			}.getType();
			employees = gson.fromJson(mat, collectionType);

		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());

		return employees;
	}

	/**
	 * Pesquisa o employee pelo cpf
	 * 
	 * GET
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/?cpf=24836399840
	 * 
	 * @param filtro EmployeeFiltro
	 * @return Employee
	 * @throws Exception
	 */
	public List<Employee> buscarDadosEmployees(EmployeeFiltro filtro) throws Exception {

		String url = employee_api + "/employees/";

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		headers.add("Authorization", authorization);

		HttpEntity<?> request = new HttpEntity<Object>(headers);

		ResponseEntity<String> response = null;

		List<Employee> employees = new ArrayList<Employee>();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("cpf", filtro.getCpf());

		try {
			response = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);

			String jsonText = response.getBody();

			if (response.getBody().contains("No employee found")) {
				throw new Exception("No employee found");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			String mat = jsonResponse.getJSONArray("values").toString();

			Gson gson = new Gson();

			java.lang.reflect.Type collectionType = new TypeToken<Collection<Employee>>() {
			}.getType();
			employees = gson.fromJson(mat, collectionType);

		} catch (final Exception ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		}

		return employees;
	}

	/**
	 * Incluir Employee Post
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/employees/
	 * 
	 * @param employee Employee
	 * @return Employee
	 * @throws Exception Exception
	 */

	public String incluirEmployee(final Employee employee) throws Exception {

		String url = employee_api + "employees/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<Employee> requestUpdate = new HttpEntity<>(employee, headers);

		ResponseEntity<String> response = null;

		System.out.println(employee.toString());
		System.out.println(employee.getLeaders());

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.POST, requestUpdate, String.class);
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		return response.getBody();
	}

	/**
	 * Atualiza o employee
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/123456
	 */
	public String updateEmployee(final Employee employee) throws Exception {
		String url = employee_api + "employees/" + employee.getIdPerson().toString() + "/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		ResponseEntity<String> response = null;

		HttpEntity<Employee> requestUpdate = new HttpEntity<>(employee, headers);

		System.out.println(employee.toString());

		try {
			response = this.restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, String.class);
			return response.getBody();
		} catch (final Exception ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * Deleta o registro
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/123456
	 */

	public String deleteEmployee(final Employee employee) throws Exception {

		String url = employee_api + "employees/" + employee.getIdPerson().toString() + "/";

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		headers.add("Authorization", authorization);

		Map<String, String> corpo = new HashMap<String, String>();
		corpo.put("id_person", employee.getIdPerson().toString());

		HttpEntity<?> request = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;
		String retorno = "";

		try {
			response = this.restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

			retorno = response.getBody();
		} catch (final Exception ex) {
			throw new Exception(ex);
		}

		return retorno;
	}

	/**
	 * Inclui a lista de employees Post
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/import/
	 */
	public String incluirListaEmployee(final List<Employee> employees) throws Exception {

		String url = employee_api + "employees/import/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		ResponseEntity<String> response = null;

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(employees, headers);

		System.out.println(employees.toString());

		try {

			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.POST, requestEntity, String.class);

		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		return response.getBody();
	}

	/**
	 * Busca employee por idPerson
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/217018/
	 * 
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	public String buscarEmployeeIdPerson(String idPerson) throws Exception {

		String url = employee_api + "/employees/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(idPerson, headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());
		return response.getBody();
	}

	/**
	 * Busca o employee por cpf
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/?cpf=24836399840
	 * 
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	public String buscarEmployee(EmployeeFiltro filtro) throws Exception {

		String url = employee_api + "/employees/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(filtro.getCpf(), headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());
		return response.getBody();
	}

	/**
	 * Inclui funcionário no grupo
	 * https://www.pulses.com.br/api/engage_sandbox/v1/employees/123456/groups/1234/
	 */
	public String incluirClienteGrupo(final String idPerson, final String idGroup) throws Exception {

		String url = employee_api + "employees/" + idPerson + "/groups/" + idGroup + "/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		Map<String, String> corpo = new HashMap<String, String>();
		corpo.put("id_person", idPerson);
		corpo.put("id_group", idGroup);

		HttpEntity<?> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.POST, requestUpdate, String.class, corpo);
			return response.getBody();
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Deleta o empregado do grupo
	 * https://www.pulses.com.br/api/engage_sandbox/v1/123456/groups/1234/
	 * 
	 * @param idPerson
	 * @param idGroup
	 * @return String
	 * @throws Exception
	 */
	public String deletarClienteGrupo(final String idPerson, final String idGroup) throws Exception {

		String url = employee_api + "employees/" + idPerson + "/groups/" + idGroup + "/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		Map<String, String> corpo = new HashMap<String, String>();
		corpo.put("id_person", idPerson);
		corpo.put("id_group", idGroup);

		HttpEntity<?> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.DELETE, requestUpdate, String.class,
					corpo);
			return response.getBody();
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Deleta o grupo https://www.pulses.com.br/api/engage_sandbox/v1/groups/123
	 */
	public String deletarGrupo(final String idGroup) throws Exception {

		String url = employee_api + "groups/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url + idGroup + "/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<?> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.DELETE, requestUpdate, String.class);
			return response.getBody();
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Retorna listagem de grupos
	 * https://www.pulses.com.br/api/engage_sandbox/v1/groups/
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Groups> buscarGroups() throws Exception {

		String url = employee_api + "/groups/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		List<Groups> groups = new ArrayList<Groups>();

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);

			String jsonText = response.getBody();

			if (response.getBody().contains("No group found")) {
				throw new Exception("No group found");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			String mat = jsonResponse.getJSONArray("values").toString();

			Gson gson = new Gson();

			java.lang.reflect.Type collectionType = new TypeToken<Collection<Groups>>() {
			}.getType();
			groups = gson.fromJson(mat, collectionType);

		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());

		return groups;
	}

	/**
	 * Busca arvore de grupos
	 * https://www.pulses.com.br/api/engage_sandbox/v1/groups/tree/
	 */
	public List<Groups> buscarArvoreGroups() throws Exception {

		String url = employee_api + "/groups/tree/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		List<Groups> groups = new ArrayList<Groups>();

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);

			String jsonText = response.getBody();

			if (response.getBody().contains("No Groups found")) {
				throw new Exception("No Groups found");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			String mat = jsonResponse.getJSONArray("values").toString();

			Gson gson = new Gson();

			java.lang.reflect.Type collectionType = new TypeToken<Collection<Groups>>() {
			}.getType();
			groups = gson.fromJson(mat, collectionType);

		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());

		return groups;
	}

	/**
	 * Incluir Grupo Post https://www.pulses.com.br/api/engage_sandbox/v1/groups/
	 */
	public String incluirGrupoPost(final String name, final String parentId) throws Exception {

		String url = employee_api + "groups/";

		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		Groups group = new Groups();
		group.setName(name);
		group.setParentId(parentId);

		Map<String, String> corpo = new HashMap<String, String>();
		corpo.put("name", name);
		corpo.put("parent_id", parentId);

		HttpEntity<Groups> requestUpdate = new HttpEntity<>(group, headers);

		ResponseEntity<String> response = null;

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.POST, requestUpdate, String.class, corpo);
			return response.getBody();
		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Busca grupo por nome
	 * https://www.pulses.com.br/api/engage_sandbox/v1/groups/find/?name=nome_grupo
	 */
	public Groups buscarDadosGroup(String name) throws Exception {

		String url = employee_api + "/groups/find/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url + "?name=" + name;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(name, headers);

		ResponseEntity<String> response = null;
		Groups groups = null;

		Map<String, String> corpo = new HashMap<String, String>();
		corpo.put("name", name);

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class, corpo);

			String jsonText = response.getBody();

			if (response.getBody().contains("No group found")) {
				throw new Exception("No group found");
			} else if (response.getBody().contains("Inform the group")) {
				throw new Exception("Informe o nome do grupo.");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			String mat = jsonResponse.getJSONArray("values").toString().replace("[", "").replace("]", "");

			Gson gson = new Gson();

			groups = gson.fromJson(mat, Groups.class);

		} catch (final Exception ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		}

		return groups;
	}

	/**
	 * Busca o grupo pelo id do grupo
	 * https://www.pulses.com.br/api/engage_sandbox/v1/groups/1234/
	 */
	public Groups buscarGroupsById(String idGroup) throws Exception {

		String url = employee_api + "/groups/";
		this.restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

		String fooResourceUrl = url + idGroup + "/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> requestUpdate = new HttpEntity<>(headers);

		ResponseEntity<String> response = null;

		Groups groups = new Groups();

		try {
			response = this.restTemplate.exchange(fooResourceUrl, HttpMethod.GET, requestUpdate, String.class);

			String jsonText = response.getBody();

			if (response.getBody().contains("No Groups found")) {
				throw new Exception("No Groups found");
			}

			int i = response.getBody().indexOf("{");

			jsonText = jsonText.substring(i);

			JSONObject jsonResponse = new JSONObject(jsonText);

			Gson gson = new Gson();

			String mat = jsonResponse.getJSONObject("values").toString();

			groups = gson.fromJson(mat, Groups.class);

		} catch (final RestClientException ex) {
			System.out.println(ex);
			throw new Exception(ex.getMessage(), ex);
		} catch (final Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage(), e);
		}

		System.out.println(response.getBody());

		return groups;
	}

	/**
	 * Atualiza o group https://www.pulses.com.br/api/engage_sandbox/v1/groups/1234/
	 */
	public String updateGroups(final Groups group) throws Exception {
		String url = employee_api + "groups/" + group.getIdGroup().toString() + "/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		ResponseEntity<String> response = null;

		HttpEntity<Groups> requestUpdate = new HttpEntity<>(group, headers);

		System.out.println(group.toString());

		try {
			response = this.restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, String.class);
			return response.getBody();
		} catch (final Exception ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * Main para testes
	 * 
	 * @param args String
	 */
	public static void main(String... args) {

		EmployeeServices employee = new EmployeeServicesImpl("https://www.pulses.com.br/api/engage_sandbox/v1/",
				"a1f0f5e01e50987039884eadcbe36265");
		EmployeeFiltro filtro = new EmployeeFiltro();
		filtro.setCpf("43433587809");

		Employee em = new Employee();
		em.setIdPerson(99219818032L);

		try {

			// **************************Employee**************************

			//employee.buscarEmployes();
			 employee.incluirEmployee(employFake().get(0));
			// employee.deleteEmployee(em);
			// employee.incluirListaEmployee(employFake());
			// employee.buscarDadosEmployees(filtro);
			// employee.updateEmployee(employFake().get(0));
			// employee.incluirClienteGrupo("99219818032", "40809");
			// employee.deletarClienteGrupo("99219818032", "40809");

			// **************************Grupos**************************

//			employee.deletarGrupo("40807");
//			employee.buscarGroups();
//			employee.buscarArvoreGroups();
//			employee.incluirGrupoPost("sanches sanches", "0");
//			employee.buscarDadosGroup("sanches sanches");
//			employee.buscarGroupsById("40809");
//
//			Groups group = new Groups();
//			group.setIdGroup("40809");
//			group.setName("sanches sanches");
//			group.setParentId("");
//
//			employee.updateGroups(group);

			// **********************************************************
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gera um employee falso
	 * 
	 * @return List<Employee>
	 */
	private static List<Employee> employFake() {

		List<Employee> listaEmployee = new ArrayList<Employee>();

		Employee em = new Employee();
		//em.setIdPerson(99219818032L);
		em.setName("Xica da Silva _2");
		em.setEmail("testeV9@gmail.com");
		em.setCpf("62773154550");
		// em.setInternalNumber("364525");
		em.setCelphone("13981587989");

		Groups grupo = new Groups();
		// grupo.setAncestors("");
		grupo.setIdGroup("40797");
		grupo.setName("BARTIRA,BARTIRA,BARTIRA,BARTIRA,BARTIRA");

		Groups[] arr = { grupo };

		em.setGroups(arr);

		// VANDERLEI SISCATI DE SA null 254.923.668-82

		Leaders leaders = new Leaders();
		leaders.setName("VANDERLEI SISCATI DE SA");
		leaders.setCpf("254.923.668-82");
		// leaders.setInternalNumber("217086");

		Leaders[] led = { leaders };
		em.setLeaders(led);

		// em.setLeaders("RENATO CEZAR ROSA, 400023868, 1");

		em.setLanguage("pt-BR");
		em.setBlocked("0");
		em.setSex("F");

		Locale locale2 = new Locale("en", "US");
		Calendar birthday = Calendar.getInstance(locale2);
		birthday.set(Calendar.YEAR, 1983);
		birthday.set(Calendar.MONTH, 9);
		birthday.set(Calendar.DAY_OF_MONTH, 4);
		birthday.set(Calendar.MINUTE, 0);
		birthday.set(Calendar.HOUR_OF_DAY, 0);
		birthday.set(Calendar.SECOND, 0);

		em.setBirthday(birthday.getTime());

		Calendar hiringDate = Calendar.getInstance(locale2);
		em.setHiringDate(hiringDate.getTime());

		Calendar resignationDate = Calendar.getInstance(locale2);
		em.setResignationdate(resignationDate.getTime());

		em.setPosition("ESPEC SISTEMA I");
		em.setScholarity("SUPERIOR COMPLETO");
		em.setUnitBusiness("MATRIZ");
		em.setUnitGeography("SÃO CAETANO DO SUL");

		listaEmployee.add(em);

		return listaEmployee;

	}

	/**
	 * Formata a data
	 * 
	 * @param dtRef   Date
	 * @param pattern String
	 * @return String
	 */
	public static String format(Date dtRef, String pattern) {
		SimpleDateFormat sdt = new SimpleDateFormat(pattern, localeDefault);
		return sdt.format(dtRef);
	}

}
