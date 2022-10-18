package sun.study.note;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import sun.study.note.util.DSUtil;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */

public class ShandongGY {

    private static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {

        String param = "{\"entityIDs\":\"Nationa_Court_Sjzw\",\"indicIDs\":\"national_ONLINE_NORMA_TOTAL,national_ONLINE_FAULT_TOTAL\",\"organId\":\"3901\",\"netId\":\"FYWX.3\"}";

        JSONObject pJson = JSONObject.parseObject(param);

        List<String> entityIDs = new ArrayList<>(Arrays.asList(pJson.getString("entityIDs").split(",")));


        List<String> indicIDs = new ArrayList<>(Arrays.asList(pJson.getString("indicIDs").split(",")));
        String organId = pJson.getString("organId");
        String netId = pJson.getString("netId");

        handler(indicIDs, entityIDs, null, organId, netId);
    }


    public static String handler(List<String> indicesIDs, List<String> entityIDs, String startTimeIn, String organId, String netId) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(URL);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        List<Map<String, Object>> finalRes = new ArrayList();
        String tmpQueryMetaSQL = "SELECT `TABLE_NAME`,`COLUMN_NAME`,COLUMN_GROUP,KEY_FIELD,START_TIME,`CONDITION` CDN,FIELD_NAME,ORGAN_COL,NET_COL FROM t_indi_config WHERE INDIC_ID = ?";

        try {
            String tmpQuerySQL;
            String tableName;
            String columnName;
            String columnGroup;
            String keyField;
            String startTime;
            String condition;
            String fieldName;
            String organCol;
            String netCol;
            String subCdn;
            String subGroupBy;
            List res = new ArrayList();
            Map metaData;
            String indicesID;
            Iterator var24;
            String indicID;
            if (entityIDs.isEmpty()) {
                var24 = indicesIDs.iterator();

                while (var24.hasNext()) {
                    indicID = (String) var24.next();
                    indicesID = indicID.trim();

                    try {
                        metaData = jdbcTemplate.queryForMap(tmpQueryMetaSQL, new Object[]{indicesID});
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "{\"error\":\"Querying CONFIG TABLE failure.\"}";
                    }

                    tableName = (String) metaData.get("TABLE_NAME");
                    columnName = (String) metaData.get("COLUMN_NAME");
                    columnGroup = (String) metaData.get("COLUMN_GROUP");
                    keyField = (String) metaData.get("KEY_FIELD");
                    startTime = (String) metaData.get("START_TIME");
                    condition = (String) metaData.get("CDN");
                    fieldName = (String) metaData.get("FIELD_NAME");
                    organCol = (String) metaData.get("ORGAN_COL");
                    netCol = (String) metaData.get("NET_COL");
                    subCdn = DSUtil.getSubConditon(organId, organCol, netId, netCol);
                    subGroupBy = DSUtil.getSubGroupBy(organId, organCol, netId, netCol);
                    if (StringUtils.isEmpty(startTimeIn)) {
                        if (StringUtils.isEmpty(condition)) {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + columnName + " IS NOT NULL ) GROUP BY " + startTime + "," + keyField + subGroupBy;
                            res = jdbcTemplate.queryForList(tmpQuerySQL);
                            finalRes.addAll(res);
                        } else {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + condition + " AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + condition + " AND " + columnName + " IS NOT NULL ) GROUP BY " + startTime + "," + keyField + subGroupBy;
                            res = jdbcTemplate.queryForList(tmpQuerySQL);
                            finalRes.addAll(res);
                        }
                    } else if (StringUtils.isEmpty(condition)) {
                        tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + "," + keyField + subGroupBy;
                        res = jdbcTemplate.queryForList(tmpQuerySQL);
                        finalRes.addAll(res);
                    } else {
                        tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + condition + " AND " + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + "," + keyField + subGroupBy;

                        System.out.println(tmpQuerySQL);
                        res = jdbcTemplate.queryForList(tmpQuerySQL);
                        finalRes.addAll(res);
                    }
                }
            } else {
                var24 = entityIDs.iterator();

                while (var24.hasNext()) {
                    indicID = (String) var24.next();
                    String entityID = indicID.trim();
                    Iterator var26 = indicesIDs.iterator();

                    while (var26.hasNext()) {
                        // String indicID = (String)var26.next();
                        // indicID = (String)var26.next();

                        indicesID = indicID.trim();

                        try {
                              metaData = new HashMap();//jdbcTemplate.queryForMap(tmpQueryMetaSQL, new String[]{indicesID});
                            List<Map<String, Object>> maps = jdbcTemplate.queryForList(tmpQueryMetaSQL, new String[]{indicesID});
                            System.out.println(maps);


                        } catch (DataAccessException e) {
                            e.printStackTrace();
                            return "{\"error\":\"Querying CONFIG TABLE failure.\"}";
                        }

                        tableName = (String) metaData.get("TABLE_NAME");
                        columnName = (String) metaData.get("COLUMN_NAME");
                        columnGroup = (String) metaData.get("COLUMN_GROUP");
                        keyField = (String) metaData.get("KEY_FIELD");
                        startTime = (String) metaData.get("START_TIME");
                        condition = (String) metaData.get("CDN");
                        fieldName = (String) metaData.get("FIELD_NAME");
                        organCol = (String) metaData.get("ORGAN_COL");
                        netCol = (String) metaData.get("NET_COL");
                        subCdn = DSUtil.getSubConditon(organId, organCol, netId, netCol);
                        subGroupBy = DSUtil.getSubGroupBy(organId, organCol, netId, netCol);
                        if (StringUtils.isEmpty(startTimeIn)) {
                            if (StringUtils.isEmpty(condition)) {
                                tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + keyField + "=? AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + keyField + "=? AND " + columnName + " IS  NOT NULL AND " + startTime + " >=DATE_SUB(CURDATE(),INTERVAL 2 MONTH)) GROUP BY " + startTime + subGroupBy;

                                System.err.println(tmpQuerySQL);
                                res = jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID, entityID});
                                finalRes.addAll(res);
                            } else {
                                tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + condition + " AND " + keyField + "=? AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + condition + " AND " + keyField + "=? AND " + columnName + " IS  NOT NULL AND " + startTime + " >=DATE_SUB(CURDATE(),INTERVAL 2 MONTH)) GROUP BY " + startTime + subGroupBy;
                                res = jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID, entityID});
                                finalRes.addAll(res);
                            }
                        } else if (StringUtils.isEmpty(condition)) {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + keyField + "=? AND " + startTime + ">=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + subGroupBy;
                            res = jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID});
                            finalRes.addAll(res);
                        } else {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + condition + " AND " + keyField + "=? AND " + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + subGroupBy;
                            res = jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID});
                            finalRes.addAll(res);
                        }
                    }
                }

                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String strFinalRes = JSONObject.toJSONString(finalRes);
        strFinalRes = strFinalRes.replaceAll("INT_ID", "ObjectId").replaceAll("NENAME", "ObjectName");
        return strFinalRes;
    }
}


