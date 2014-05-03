package Persistence;

import twitter4j.auth.AccessToken;
import Exceptions.DAOExcepcion;

public class DAL {

	private static DAL dal;
	private DAL()throws DAOExcepcion{
		OAuthDAO = new OAuthDAOImp();
		DatosUsuarioDAO = new DatosUsuarioDAOImp();
	}
	public static DAL getInstance() throws DAOExcepcion{
		if (dal==null) dal = new DAL();
		return dal;
	}
	IOAuthDAO OAuthDAO;
	IDatosUsuario DatosUsuarioDAO;
	public void StoreAccessToken(long useId, AccessToken accessToken,
			Bussiness.Properties prop) throws Exception {
		OAuthDAO.StoreAccessToken(useId, accessToken, prop);
	}

}
