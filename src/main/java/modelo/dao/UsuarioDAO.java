/**
 * 
 */
package modelo.dao;

/**
 * @author gueva
 *
 */
public interface UsuarioDAO<T,ID> extends GenericDAO<T, ID> {
	public T autorizar(ID identificacion , String contraseña);
}
