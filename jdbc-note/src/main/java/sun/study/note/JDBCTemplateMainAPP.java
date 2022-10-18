package sun.study.note;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import sun.study.note.util.DSUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-29
 */

public class JDBCTemplateMainAPP {

    private static final String URL = "jdbc:mysql://localhost:3306/law?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        test1();
    }


    public static void test1() throws Exception {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");


        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(" SELECT * FROM t_indi_config ");
        System.out.println(maps);
    }

    public static String selectIndicValueWide(List<String> indicesIDs, List<String> entityIDs, String startTimeIn, String organId, String netId) {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");


        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource);

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
            List res;
            Map metaData;
            String indicesID;
            Iterator var24;
            String indicID;
            if (entityIDs.isEmpty()) {
                var24 = indicesIDs.iterator();

                while(var24.hasNext()) {
                    indicID = (String)var24.next();
                    indicesID = indicID.trim();

                    try {
                        metaData = jdbcTemplate.queryForMap(tmpQueryMetaSQL, new Object[]{indicesID});
                    } catch (Exception var30) {

                        return "{\"error\":\"Querying CONFIG TABLE failure.\"}";
                    }

                    tableName = (String)metaData.get("TABLE_NAME");
                    columnName = (String)metaData.get("COLUMN_NAME");
                    columnGroup = (String)metaData.get("COLUMN_GROUP");
                    keyField = (String)metaData.get("KEY_FIELD");
                    startTime = (String)metaData.get("START_TIME");
                    condition = (String)metaData.get("CDN");
                    fieldName = (String)metaData.get("FIELD_NAME");
                    organCol = (String)metaData.get("ORGAN_COL");
                    netCol = (String)metaData.get("NET_COL");
                    subCdn = DSUtil.getSubConditon(organId, organCol, netId, netCol);
                    subGroupBy = DSUtil.getSubGroupBy(organId, organCol, netId, netCol);
                    if (StringUtils.isEmpty(startTimeIn)) {
                        if (StringUtils.isEmpty(condition)) {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + columnName + " IS NOT NULL ) GROUP BY " + startTime + "," + keyField + subGroupBy;
                            res =  jdbcTemplate.queryForList(tmpQuerySQL);
                            finalRes.addAll(res);
                        } else {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + condition + " AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + condition + " AND " + columnName + " IS NOT NULL ) GROUP BY " + startTime + "," + keyField + subGroupBy;
                            res =  jdbcTemplate.queryForList(tmpQuerySQL);
                            finalRes.addAll(res);
                        }
                    } else if (StringUtils.isEmpty(condition)) {
                        tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + "," + keyField + subGroupBy;
                        res =  jdbcTemplate.queryForList(tmpQuerySQL);
                        finalRes.addAll(res);
                    } else {
                        tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + columnName + " IS NOT NULL AND " + subCdn + condition + " AND " + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + "," + keyField + subGroupBy;
                        res =  jdbcTemplate.queryForList(tmpQuerySQL);
                        finalRes.addAll(res);
                    }
                }
            } else {
                var24 = entityIDs.iterator();

                while(var24.hasNext()) {
                    indicID = (String)var24.next();
                    String entityID = indicID.trim();
                    Iterator var26 = indicesIDs.iterator();

                    while(var26.hasNext()) {
                        // String indicID = (String)var26.next();
                        // indicID = (String)var26.next();

                        indicesID = indicID.trim();

                        try {
                            metaData =  jdbcTemplate.queryForMap(tmpQueryMetaSQL, new Object[]{indicesID});
                        } catch (DataAccessException var29) {

                            return "{\"error\":\"Querying CONFIG TABLE failure.\"}";
                        }

                        tableName = (String)metaData.get("TABLE_NAME");
                        columnName = (String)metaData.get("COLUMN_NAME");
                        columnGroup = (String)metaData.get("COLUMN_GROUP");
                        keyField = (String)metaData.get("KEY_FIELD");
                        startTime = (String)metaData.get("START_TIME");
                        condition = (String)metaData.get("CDN");
                        fieldName = (String)metaData.get("FIELD_NAME");
                        organCol = (String)metaData.get("ORGAN_COL");
                        netCol = (String)metaData.get("NET_COL");
                        subCdn = DSUtil.getSubConditon(organId, organCol, netId, netCol);
                        subGroupBy = DSUtil.getSubGroupBy(organId, organCol, netId, netCol);
                        if (StringUtils.isEmpty(startTimeIn)) {
                            if (StringUtils.isEmpty(condition)) {
                                tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + keyField + "=? AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + keyField + "=? AND " + columnName + " IS  NOT NULL AND " + startTime + " >=DATE_SUB(CURDATE(),INTERVAL 2 MONTH)) GROUP BY " + startTime + subGroupBy;
                                res =  jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID, entityID});
                                finalRes.addAll(res);
                            } else {
                                tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + condition + " AND " + keyField + "=? AND " + startTime + "=(SELECT max(" + startTime + ") FROM " + tableName + " WHERE " + subCdn + condition + " AND " + keyField + "=? AND " + columnName + " IS  NOT NULL AND " + startTime + " >=DATE_SUB(CURDATE(),INTERVAL 2 MONTH)) GROUP BY " + startTime + subGroupBy;
                                res =  jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID, entityID});
                                finalRes.addAll(res);
                            }
                        } else if (StringUtils.isEmpty(condition)) {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + keyField + "=? AND " + startTime + ">=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + subGroupBy;
                            res =  jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID});
                            finalRes.addAll(res);
                        } else {
                            tmpQuerySQL = "SELECT '" + indicesID + "' IndicId," + (StringUtils.isEmpty(fieldName) ? " " : fieldName) + keyField + " ObjectId,DATE_FORMAT(" + startTime + ",'%Y-%m-%d %H:%i:%s') StartTime,IFNULL(" + columnGroup + "(" + columnName + "),0) IndicValue FROM " + tableName + " WHERE " + subCdn + columnName + " IS  NOT NULL AND " + condition + " AND " + keyField + "=? AND " + startTime + "=DATE_FORMAT('" + startTimeIn + "','%Y-%m-%d %H:%i:%s') GROUP BY " + startTime + subGroupBy;
                            res =  jdbcTemplate.queryForList(tmpQuerySQL, new Object[]{entityID});
                            finalRes.addAll(res);
                        }
                    }
                }
            }
        } catch (Exception var31) {
            return "{\"error\":\"Querying TARGET TABLE failure.\"}";
        }

        String strFinalRes = JSONObject.toJSONString(finalRes);
        strFinalRes = strFinalRes.replaceAll("INT_ID", "ObjectId").replaceAll("NENAME", "ObjectName");
        return strFinalRes;
    }
}


