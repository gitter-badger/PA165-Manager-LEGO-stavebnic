package cz.muni.fi.PA165.service;

import org.springframework.dao.DataAccessException;

public class DataAccessExceptionService extends DataAccessException {

    public DataAccessExceptionService(String msg) {
        super(msg);
    }
}
