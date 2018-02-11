package com.sinosoft.redMine.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.sinosoft.redMine.service.RedMineImportImpl;
import com.sinosoft.redMine.util.MysqlUtil;

public class RunRedMine {
	
    private static Properties property;  
    static{  
        String url=MysqlUtil.class.getClassLoader().getResource("ImportTool.properties").getPath();  
        try {  
            property=new Properties();  
            property.load(new FileInputStream(url));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	
	public static void main(String[] args) {
		
		RedMineImportImpl redMineImportImpl = new RedMineImportImpl();
		System.out.println(property.getProperty("filePath"));
		redMineImportImpl.doImportIssue(property.getProperty("filePath"));
		
	}
	
	
	
	
}
