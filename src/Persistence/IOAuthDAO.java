package Persistence;

import twitter4j.auth.AccessToken;
import Exceptions.DAOExcepcion;

public interface IOAuthDAO {
	public void StoreAccessToken(long useId, AccessToken accessToken, Bussiness.Properties prop) throws DAOExcepcion, Exception;

}
