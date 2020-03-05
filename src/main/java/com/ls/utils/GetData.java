package com.ls.utils;

import net.sf.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by chenjun
 * @desc单一窗口json解析
 * @create 2019/6/20.
 **/
public class GetData {

    protected JdbcTemplate jt;

    public JdbcTemplate getJt() {
        return jt;
    }

    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }

    public static void main(String[] args) {
        String json="[{\"codeName\":\"《国际船舶保安证书》(海事)\",\"codeValue\":\"0001\"},{\"codeName\":\"《临时国际船舶保安证书》(海事)\",\"codeValue\":\"0002\"},{\"codeName\":\"船舶保安信息材料复印件〔适用客船（包括高速客船）及500总吨及以上的货船〕（必要时）(海事)\",\"codeValue\":\"0003\"},{\"codeName\":\"国际防止油污证书（IOPP证书）及其附件（格式B）的复印件(海事)\",\"codeValue\":\"0004\"},{\"codeName\":\"CAS检验报告的复印件(海事)\",\"codeValue\":\"0005\"},{\"codeName\":\"所载运油品名称以及15℃时密度、50℃时流动粘度（燃油）的说明文书（必要时）(海事)\",\"codeValue\":\"0006\"},{\"codeName\":\"《油污损害民事责任保险或其他财务保证证书》(海事)\",\"codeValue\":\"0007\"},{\"codeName\":\"《燃油污染损害民事责任保险或其他财务保证证书》复印件（1000总吨以上的非油船和所有的油船）(海事)\",\"codeValue\":\"0008\"},{\"codeName\":\"专项护航申请（需要时）(海事)\",\"codeValue\":\"0009\"},{\"codeName\":\"经批准的危险货物进港申报单复印件（必要时）(海事)\",\"codeValue\":\"0010\"},{\"codeName\":\"危险货物舱单（无危险货物者免）(海事)\",\"codeValue\":\"0011\"},{\"codeName\":\"船舶适航、检验相关证书(海事)\",\"codeValue\":\"0012\"},{\"codeName\":\"船员证书（适用于外国籍船舶）(海事)\",\"codeValue\":\"0013\"},{\"codeName\":\"上一港出口许可证或其他证明材料（因中途改港，船舶目的港发生变化，导致与上一港《出口岸许可证》中的“驶往港”不一致的）(海事)\",\"codeValue\":\"0014\"},{\"codeName\":\"船舶落实护航措施的证明（需要护航时）(海事)\",\"codeValue\":\"0015\"},{\"codeName\":\"委托证明及委托人和被委托人身份证明及其复印件（委托时）(海事)\",\"codeValue\":\"0016\"},{\"codeName\":\"办理远洋渔船进口岸手续提交材料“货物申报单”为“货物（渔获物）申报单”(海事)\",\"codeValue\":\"0017\"},{\"codeName\":\"强制措施已经依法解除的证明材料（如果采取了禁止船舶航行的司法或者行政强制措施）(海事)\",\"codeValue\":\"0018\"},{\"codeName\":\"船舶安全检查记录簿及其复印件（中国籍船舶）(海事)\",\"codeValue\":\"0019\"},{\"codeName\":\"获准往国外海域或远洋捕捞作业(许可)批文（远洋渔船）(海事)\",\"codeValue\":\"0020\"},{\"codeName\":\"经其他查验单位签署的《船舶出口岸手续联系单》(海事)\",\"codeValue\":\"0021\"},{\"codeName\":\"海事部门要求递交的其他资料(海事)\",\"codeValue\":\"0022\"}]";

        JSONArray jsonArray = JSONArray.fromObject(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, Object> params = new HashMap<String, Object>();
           String insertSql="insert into C_SYS_COD(FLD_ID,FLD_NAM,SYS_COD,C_MEAN_STR,E_MEAN_STR,RECORD_TIM,RECORD_MAN,DEPT_COD,USERID,TENTANTID) values  ('HAISHI_FILETYPE','附件类型','" + jsonArray.getJSONObject(i).getString("codeValue") +"','"+ jsonArray.getJSONObject(i).getString("codeName") +"',"+"' '"+",sysdate"+",'CD001'"+",'O001S008S001S002S001O001S003'"+",'4062EABE9D1B6562E0530100007FC571'"+",'O001S008S001S002S001O001'" +")";
//            String insertSql="insert into C_BERTH(BERTH_NO,C_BERTH_NAM,COUNTRY_COD,PORT_COD,WORK_PLACE_COD,BERTH_TYPE_ID) values  ('" + jsonArray.getJSONObject(i).getString("codeValue") +"','"+ jsonArray.getJSONObject(i).getString("codeName") +"',"+"'CN'"+",'CNYPG'"+",'CNYPG'"+",'B'" +")";
//            String insertSql="insert into CHECK_AGENCY(CHECK_CODE,C_CHECK_NAM,RECORD_TIM,RECORD_MAN,DEPT_COD,USERID,TENTANTID) values  ('" + jsonArray.getJSONObject(i).getString("codeValue") +"','"+ jsonArray.getJSONObject(i).getString("codeName")  +"'"+",sysdate"+",'CD001'"+",'O001S008S001S002S001O001S003'"+",'4062EABE9D1B6562E0530100007FC571'"+",'O001S008S001S002S001O001'" +")";
//             String insertSql="insert into C_MSA_AGENCY(MSA_COD,C_MSA_NAM,RECORD_TIM,RECORD_MAN,DEPT_COD,USERID,TENTANTID) values  ('" + jsonArray.getJSONObject(i).getString("codeValue") +"','"+ jsonArray.getJSONObject(i).getString("codeName")  +"'"+",sysdate"+",'CD001'"+",'O001S008S001S002S001O001S003'"+",'4062EABE9D1B6562E0530100007FC571'"+",'O001S008S001S002S001O001'" +")";
            System.out.println(insertSql+";");
        }
    }

//        System.out.println(111);

//    }
}
