package com.quasar.sika.design.server;


import com.sika.code.GenerateCodeHandler;
import com.sika.code.GeneratorCodeDTO;

/**
 * 代码生成器启动类
 *
 * @author daiqi
 * @create 2019-05-09 15:32
 */
public class GenerateCodeApplication {
    private static String author = "daiqi";

    public static void main(String[] args) {
        String [] tableNames = {"sika_config","sika_dept","sika_dict_data","sika_dict_type","sika_logininfor"
                ,"sika_menu","sika_notice","sika_oper_log","sika_post","sika_role","sika_role_dept","sika_role_menu","sika_user_online"
                ,"sika_user_post","sika_user_role"};
        for (String tableName : tableNames) {
            GeneratorCodeDTO generatorCodeDTO = GenerateCodeConfig.buildGeneratorCodeDTO(tableName, author);
            new GenerateCodeHandler().generateCode(generatorCodeDTO);
        }
    }
}
