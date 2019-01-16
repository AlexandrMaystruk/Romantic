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
//    public void insertParticipant(ParticipantTable participantTable) {
//        INSTANCE.participantDao().insertAll(participantTable);
//    }
//
//    public void insertParticipant(List<ParticipantTable> participants) {
//        INSTANCE.participantDao().insertList(participants);
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
//    public List<ParticipantTable> getParticipantsByHikeId(int hikeId) {
//
//        List<ParticipantTable> group = new ArrayList<>();
//        List<HikesParticipantsTable> listIds = INSTANCE.hikesParticipantsDAO().getListIdByHikeId(hikeId);
//
//        for (int i = 0; i < listIds.size(); i++) {
//
//            if (null != INSTANCE.participantDao().getParticipantById(listIds.get(i).getIdParticipant())) {
//                group.add(INSTANCE.participantDao().getParticipantById(listIds.get(i).getIdParticipant()));
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
//    public List<ParticipantTable> getAllParticipants() {
//        return INSTANCE.participantDao().getAllParticipants();
//    }
//
//    public ParticipantTable getParticipantById(String id) {
//        return INSTANCE.participantDao().getParticipantById(id);
//    }
//
//
//    public void updateParticipant(ParticipantTable participant) {
//        INSTANCE.participantDao().update(participant);
//    }
//
//    public void updateListParticipant(List<ParticipantTable> list) {
//        INSTANCE.participantDao().update(list);
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
//    public void deleteListParticipant(List<ParticipantTable> participants) {
//        INSTANCE.participantDao().delete(participants);
//    }
//
//
//    public void dropAndInsertParticipant(List<ParticipantTable> list) {
//        INSTANCE.participantDao().dropTable();
//        INSTANCE.participantDao().insertList(list);
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
