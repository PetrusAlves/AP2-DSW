
package bean;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entidade.Jogo;

@ManagedBean
@SessionScoped
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> lista = new ArrayList<Jogo>();

	public void salvar() {
		try {
			if (validarCamposNulo()) {
				if(validarNumeros()) {
				jogo.setDataCadastro(new Date());
				jogo.setNumeroSecreto(new Random().nextInt(5) + 1);
				JogoDAO.salvar(jogo);
				jogo = new Jogo();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Numeros Cadastrados"));
			}
		}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void excluir(Integer id) {
		JogoDAO.excluir(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Excluido com sucesso!!", "Numeros excluidos com sucesso!"));
	}

	public void editar(Jogo jogo) {
		JogoDAO.editar(jogo);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Editado com sucesso!!", "Numeros editado com sucesso!"));
	}

	public void numeroSorteado() {
		Random numSorteado = new Random();
	}

	public List<Jogo> getLista() {
		if (lista != null) {
			lista = JogoDAO.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

	public void verificarSorteado(Jogo jogo) {
		boolean contem = jogo.getNumeroAposta().equals(jogo.getNumeroSecreto());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Contem o numero Sorteado?", (contem ? "Acertou" : "Não Acertou")));
	}

	private boolean validarNumeros() {
		
		boolean camposValidos = true;
		
		if (jogo.getNumeroAposta() < 1 || jogo.getNumeroAposta() > 5){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Os valores devem estar entre 1 e 5!"));
			camposValidos = false;
		}
		return camposValidos;
	}
	
	private boolean validarCamposNulo() {
	    boolean camposValidos = true;
	    
	    if(jogo.getNumeroAposta() == null || jogo.getNumeroAposta().equals("")
	    		|| jogo.getNomeJogador() == null || jogo.getNomeJogador().isEmpty()) {
	        
	    	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Todos os campos devem está preenchidos!"));
	        camposValidos = false;
	    } 
	        if(jogo.getNomeJogador().matches(".*\\d.*")) {
	    	 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O nome não pode conter numeros!"));
		        camposValidos = false;
	     }
	    
	    return camposValidos;
	}

	public int quantidadeElementos() {
		int contar = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (Jogo contagem : lista) {
				contar++;
			}
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Quantidade de Jogos Cadastrados!", "" + contar));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um erro ao salvar as alterações."));
			e.printStackTrace();
		}
		return contar;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

}