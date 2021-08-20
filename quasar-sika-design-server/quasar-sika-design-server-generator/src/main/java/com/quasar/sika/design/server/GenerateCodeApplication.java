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
        String [] tableNames = {"test_order"};
        for (String tableName : tableNames) {
            GeneratorCodeDTO generatorCodeDTO = GenerateCodeConfig.buildGeneratorCodeDTO(tableName, author);
            new GenerateCodeHandler().generateCode(generatorCodeDTO);
        }
    }
}
