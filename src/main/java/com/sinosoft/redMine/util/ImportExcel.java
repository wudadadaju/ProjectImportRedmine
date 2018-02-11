package com.sinosoft.redMine.util;

import java.io.File;
import java.util.List;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;

import com.sinosoft.redMine.dto.RedMineExcelDto;

public class ImportExcel {
	
	/**
     * redMine���룬excel����
     * ֻ����һҳ
     * @param
     * @return
     * @author wupengju@sinosoft.com.cn
     */
    public static List<RedMineExcelDto> importParamsToList(String path)  throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);     //����������,Ĭ��0
        params.setHeadRows(1);      //��ͷ����,Ĭ��1
        params.setNeedSave(false);       //�Ƿ���Ҫ�����ϴ���Excel
        params.setSheetNum(1);
        File f = new File(path);
        if(!f.exists()){
            return null;
        }
        System.out.println(f);
        System.out.println(params);
        List<RedMineExcelDto> list = ExcelImportUtil.importExcel(f, RedMineExcelDto.class, params);
        return list;
    }
	
	
	
	
}
