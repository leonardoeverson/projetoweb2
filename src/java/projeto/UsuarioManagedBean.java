/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import dao.UsuarioDAO;
import entidades.Usuario;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import util.SessionUtils;

/**
 *
 * @author leonardo
 */

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioManagedBean {
    public List<Usuario> usuarios;
    public Usuario usuarioConectado;
    
    private int id;
    private String nmUsuario;
    private String senhaUsuario;
    private String mensagem;   
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    
    public int getId() {
        return id;
    }

    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    public void setUsuarioConectado(Usuario usuarioConectado) {
        this.usuarioConectado = usuarioConectado;
    }
    
    public String cadastrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNmUsuario(this.getNmUsuario());
        usuario.setSenhaUsuario(this.getSenhaUsuario());
        usuario.setDtCadastro(Timestamp.from(Instant.now()));
       
        
        setNmUsuario("");
        setSenhaUsuario("");
        setMensagem("");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        try {
            usuarioDAO.inserir(usuario);
            this.id = usuario.getId();
        } catch (Exception ex) {
            setMensagem("Erro ao cadastrar a venda: "+ex);
            return "fracasso";
        }
        setMensagem("Usuario cadastrado com sucesso, prossiga com o login!");
        return "sucesso";
    }
    
    
    public String validarLogin(){
        UsuarioDAO usuDAO = new UsuarioDAO();        
        try {           
            usuarios = usuDAO.getUsuarioByNomeSenha(this.getNmUsuario(), this.getSenhaUsuario());
            
            if(usuarios.isEmpty()){
                setMensagem("Credenciais invalidas!");
                return "fracasso";
            } else {
                HttpSession session = SessionUtils.getSession();
		session.setAttribute("usuario", this.getNmUsuario());
                
                for(Usuario u: usuarios){
                    System.out.println(u.getId());
                    if(u.getId() != 0){
                        session.setAttribute("idUsuario", u.getId());
                    }
                }
                return "sucesso";
            }          
        } catch (Exception ex) {
            setMensagem("Erro ao validar login!");
            return "fracasso";
        }
        
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
    
    public Integer getIdUsuarioConectado() {
        HttpSession session = SessionUtils.getSession();
	return Integer.parseInt(session.getAttribute("idUsuario").toString());
    }
    
    public List<Usuario> getInfoUsuarioConectado(){
        UsuarioDAO usuDAO = new UsuarioDAO();        
        try {
            usuarioConectado = usuDAO.obter(getIdUsuarioConectado());  
            usuarios = new ArrayList<Usuario>();
            usuarios.add(usuarioConectado);
        } catch (Exception ex) {
            setMensagem("Erro ao obter informações!");
        }     
        return usuarios;
    }
    
    public String excluirUsuario(int id){
        UsuarioDAO usuDAO = new UsuarioDAO();          
        try {
            usuDAO.remover(id);           
            return logout();
        } catch (Exception ex) {
            setMensagem("Houve um erro ao exluir o produto:"+ex);
            return "fracasso";
        }
    }
    
    public String getUsuarioAlteracao(int id){              
        try {
            UsuarioDAO usuDAO = new UsuarioDAO(); 
            usuarioConectado = usuDAO.obter(getIdUsuarioConectado());  
            usuarioConectado.setSenhaUsuario("");
            return "altera";
        } catch (Exception ex) {           
            setMensagem("Erro ao obter informações!");
            return "fracasso";
        }            
    }
    
    public String alterarUsuario(){              
        try {
            UsuarioDAO usuDAO = new UsuarioDAO(); 
            usuDAO.atualizar(usuarioConectado);
        } catch (Exception ex) {
            setMensagem("Erro ao alterar senha!");
            return "fracasso";
        }
        
        setMensagem("Senha alterada com sucesso!");
        return "sucesso";
    }
   
}
