package com.fabio.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;

import com.fabio.dao.AutomovelDao;
import com.fabio.dao.MarcaDao;
import com.fabio.modelo.Automovel;
import com.fabio.modelo.Marca;

@ManagedBean(name="automovelMB")
@ViewScoped
public class AutomovelBean implements Serializable{
	private static final long serialVersionUID = -4919714291547443589L;
	private Automovel automovel;
	private LazyDataModel<Automovel> automoveis;
	private Marca marca;
	private String idMarca; 
	private AutomovelDao automovelDao;
	private MarcaDao marcaDao;
	private Boolean emEdicao;
	
	
	@PostConstruct
	public void init(){
		automovelDao = new AutomovelDao();
		marcaDao = new MarcaDao();
		aplicarEstadoInicial();
	}
	
	private void aplicarEstadoInicial(){
		automovel = new Automovel();
		emEdicao = false;
		idMarca = "";
		listar();
	}
	
	public void salvar(){
		automovelDao.salvar(automovel);
		aplicarEstadoInicial();
		exibirMensagem("Inclusão", "Registro incluído com sucesso");
	}

	public void alterar(){
		automovelDao.alterar(automovel);
		aplicarEstadoInicial();
		exibirMensagem("Alteração", "Registro alterado com sucesso");
	}

	public void listar(){
		if(automoveis == null){
			automoveis = automovelDao.listarDataModel();
		}
	}

	public void excluir(Automovel automovel){
		automovelDao.excluir(automovel);
		aplicarEstadoInicial();
		exibirMensagem("Exclusão", "Excluído com sucesso");
	}

	public void editar(Automovel automovel){
		this.automovel = automovel;
		emEdicao = true;
		if(automovel.getModelo() != null && automovel.getModelo().getMarca() != null){
			idMarca = automovel.getModelo().getMarca().getId().toString();
		}
	}

	private void exibirMensagem(String titulo, String mensagem) {
		FacesMessage mensagemGlobal = new FacesMessage(titulo, mensagem);
		FacesContext.getCurrentInstance().addMessage(null, mensagemGlobal);
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public LazyDataModel<Automovel> getAutomoveis() {
		return automoveis;
	}

	public void setAutomoveis(LazyDataModel<Automovel> automoveis) {
		this.automoveis = automoveis;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(String idMarca) {
		if(idMarca != null && !idMarca.equals("")){
			marca = marcaDao.buscar(Long.valueOf(idMarca));
		}
		this.idMarca = idMarca;
	}

	public Boolean getEmEdicao() {
		return emEdicao;
	}

	public void setEmEdicao(Boolean emEdicao) {
		this.emEdicao = emEdicao;
	}
	
}
