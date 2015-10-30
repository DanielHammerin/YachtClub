package model;

import java.sql.SQLException;

/**
 * Created by Daniel on 2015-10-22.
 */
public class MemberModel {

    /**
     * Method for storing in database.
     * @param newMember
     * @return
     */
    public void saveMember(Member newMember) {
        SQLDAO.saveMember(newMember);
    }

    public void changeMember(Member mem) {
        try {
            SQLDAO.updateMember(mem);
        }
        catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }

    public void deleteMember(String id) {
        try {
            SQLDAO.deleteMember(id);
        }
        catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
                System.out.println("Database connection error.");
                e.printStackTrace();
            }
    }
}
