package dao;

import dao.DAOImpl.RecordDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final RecordDAO recordDAO = new RecordDAOImpl();

    private DAOFactory(){}

    public RecordDAO getRecordDAO() {
        return recordDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }


}
