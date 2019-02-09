package maystruks08.gmail.com.data.room;//package maystruks08.gmail.com.data.sources.room;
//
//import android.content.Context;
//import maystruks08.gmail.com.data.AppDatabase;
//import maystruks08.gmail.com.data.sources.room.entity.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelper {
//
//    private static AppDatabase INSTANCE;
//    private static DatabaseHelper DATABASE_INSTANCE;
//
//    private DatabaseHelper(Context c) {
//
//        INSTANCE = AppDatabase.getAppDatabase(c);
//    }
//
//    public static DatabaseHelper getInstance(Context c) {
//
//        if (null == DATABASE_INSTANCE) {
//            DATABASE_INSTANCE = new DatabaseHelper(c);
//        }
//        return DATABASE_INSTANCE;
//    }
//
//
//    public void insertHike(HikeTable hikeTable) {
//        INSTANCE.hikeDao().insertAll(hikeTable);
//    }
//
//    public void insertParticipant(UserTable participantTable) {
//        INSTANCE.userDao().insertAll(participantTable);
//    }
//
//    public void insertParticipant(List<UserTable> participants) {
//        INSTANCE.userDao().insertList(participants);
//    }
//
//    public void insertTool(ToolsTable tools) {
//        INSTANCE.toolsDAO().insert(tools);
//    }
//
//    public void insertAllToolsList(List<ToolsTable> tools) {
//        INSTANCE.toolsDAO().insertAll(tools);
//    }
//
//    public void dropAndInsertParticipantToHikeGroup(List<HikesParticipantsTable> hikesParticipantsTableList) {
//
//        INSTANCE.hikesParticipantsDAO().dropTable();
//        INSTANCE.hikesParticipantsDAO()
//                .insert(hikesParticipantsTableList);
//    }
//
//
//    public List<UserTable> getParticipantsByHikeId(int hikeId) {
//
//        List<UserTable> group = new ArrayList<>();
//        List<HikesParticipantsTable> listIds = INSTANCE.hikesParticipantsDAO().getListIdByHikeId(hikeId);
//
//        for (int i = 0; i < listIds.size(); i++) {
//
//            if (null != INSTANCE.userDao().getUserById(listIds.get(i).getIdParticipant())) {
//                group.add(INSTANCE.userDao().getUserById(listIds.get(i).getIdParticipant()));
//            }
//        }
//        return group;
//    }
//
//    public List<ToolsTable> getToolsList() {
//        return INSTANCE.toolsDAO().getAll();
//    }
//
//    public List<ToolsTable> getToolsListByHikeId(String hikeId) {
//        return INSTANCE.toolsDAO().getByHikeId(hikeId);
//    }
//
//    public List<HikeToolsTable> getHikeToolsList() {
//        return INSTANCE.hikeToolsDAO().getAll();
//    }
//
//    public List<HikeToolsTable> getHikeToolsListByHikeId(String hikeIdInFireStore) {
//
//        return INSTANCE.hikeToolsDAO().getByHikeId(hikeIdInFireStore);
//
//    }
//
//
//    //могут быть багулички
//    public List<HikeTable> getHikesByUserIdAndTypeHike(String userId, int typeHike) {
//        List<HikesParticipantsTable> listHikePartObj = INSTANCE.hikesParticipantsDAO()
//                .getListHikeIdByParticipantId(userId);
//
//        List<String> hikeId = new ArrayList<>();
//        for (HikesParticipantsTable hp : listHikePartObj) {
//
//            hikeId.add(hp.getIdHike());
//        }
//
//        List<HikeTable> hikeTableList = new ArrayList<>();
//        for (int i = 0; i < hikeId.size(); i++) {
//            HikeTable h = INSTANCE.hikeDao().getByIdAndType(hikeId.get(i), typeHike);
//            if (h != null) {
//                hikeTableList.add(h);
//            }
//
//        }
//        return hikeTableList;
//    }
//
//    public List<UserTable> getAllUsers() {
//        return INSTANCE.userDao().getAllUsers();
//    }
//
//    public UserTable getUserById(String id) {
//        return INSTANCE.userDao().getUserById(id);
//    }
//
//
//    public void updateParticipant(UserTable participant) {
//        INSTANCE.userDao().update(participant);
//    }
//
//    public void updateListParticipant(List<UserTable> list) {
//        INSTANCE.userDao().update(list);
//    }
//
//    public void updateListTools(List<ToolsTable> tool) {
//
//        INSTANCE.toolsDAO().update(tool);
//    }
//
//    public void updateHikeListTools(List<HikeToolsTable> tool) {
//
//        INSTANCE.hikeToolsDAO().update(tool);
//    }
//
//
//    public void deleteListTools(List<ToolsTable> toolsTableList) {
//
//        INSTANCE.toolsDAO().update(toolsTableList);
//
//    }
//
//    public void deleteTool(HikeToolsTable tool) {
//        INSTANCE.hikeToolsDAO().delete(tool);
//    }
//
//    public void deleteHikeListTools(List<HikeToolsTable> toolsDBList) {
//
//        INSTANCE.hikeToolsDAO().update(toolsDBList);
//
//    }
//
//    public void deleteListParticipant(List<UserTable> participants) {
//        INSTANCE.userDao().delete(participants);
//    }
//
//
//    public void dropAndInsertParticipant(List<UserTable> list) {
//        INSTANCE.userDao().dropTable();
//        INSTANCE.userDao().insertList(list);
//    }
//
//    public void dropAndInsertHikes(List<HikeTable> list) {
//        INSTANCE.hikeDao().dropTable();
//        INSTANCE.hikeDao().insertAll(list);
//    }
//
//    public void dropAndInsertTools(List<ToolsTable> list) {
//        INSTANCE.toolsDAO().dropTable();
//        INSTANCE.toolsDAO().insertAll(list);
//    }
//
//    public void dropAndInsertHikeTools(List<HikeToolsTable> list) {
//        INSTANCE.hikeToolsDAO().dropTable();
//        INSTANCE.hikeToolsDAO().insertAll(list);
//    }
//
//
//}
